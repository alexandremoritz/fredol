package alexandremoritz.com.fredol;


import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.android.data.geojson.GeoJsonLayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.android.volley.Response;

import alexandremoritz.com.fredol.JsonTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;


    private Context context;


    private List<Cinema> cinemas;
    private GoogleMap googleMap;


    String JsonURL = "https://opendata.paris.fr/explore/dataset/cinemas-a-paris/download/?format=geojson&timezone=Europe/Berlin";
    String data = "";
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate : Starting");

        //String test = ping("google.fr");
        //Log.d(TAG,test);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up the ViewPager with the sections adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_refresh);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refresh", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //Connection to the website and Download the JSON
                try {
                    volley();
                } catch (Exception e) {
                    Log.e("Exception caught", e.toString());
                }

                Bundle args = new Bundle();
                args.putSerializable("List",(Serializable)cinemas);
                ListFragment.putArguments(args);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Explication:
                Intent intent = new Intent(this, ExplicationActicity.class);
                startActivity(intent);
                return true;
            case R.id.Sources:
                Intent intent2 = new Intent(this, SourcesActivity.class);
                startActivity(intent2);
                return true;
            case R.id.lib:
                Intent intent3 = new Intent(this, LibActivity.class);
                startActivity(intent3);
                return true;
            case R.id.credits:
                Intent intent4 = new Intent(this, CreditActivity.class);
                startActivity(intent4);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }





    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ListFragment(), "List");
        adapter.addFragment(new MapFragment(), "Map");
        adapter.addFragment(new InfoFragment(), "Info");
        viewPager.setAdapter(adapter);
    }





    public void volley() {

        // Creates the Volley request queue
        requestQueue = Volley.newRequestQueue(this);


        String url = "https://opendata.paris.fr/explore/dataset/cinemas-a-paris/download/?format=json&timezone=Europe/Berlin";

        // Casts results into the TextView found within the main layout XML with id jsonData

        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i(TAG, response.toString());

                        Gson gson = new GsonBuilder().create();

                        List<Cinema> cinemas = Arrays.asList(gson.fromJson(String.valueOf(response), Cinema[].class));

                        Log.i(TAG, cinemas.size() + " cinémas chargés.");
                        for (Cinema cine : cinemas) {
                            Log.i(TAG, cine.getFields().getNomEtablissement() + ": " + cine.getFields().getAdresse() + ":" +
                                    cine.getFields().getCoordonnees());

                        }

                        //Bundle args = new Bundle();
                        //args.putSerializable("List",(Serializable)cinemas);
                        //MapFragment.putArguments(args);

                        ListView mListView = (ListView) findViewById(R.id.listView);


                        CinemaAdapter adapter = new CinemaAdapter(MainActivity.this, cinemas);

                        mListView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }

        });

        // Adds the JSON object request "req" to the request queue
        requestQueue.add(req);

    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void addMarker() {
        cinemas = getCinemas();

    }


    public void setgoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    public GoogleMap getgoogleMap() {
        return googleMap;
    }
}



