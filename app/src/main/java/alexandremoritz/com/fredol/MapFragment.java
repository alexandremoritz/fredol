package alexandremoritz.com.fredol;

/**
 * Created by morit on 26/11/2017.
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by morit on 26/11/2017.
 */

public class MapFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {
    private static final String TAG = "MapFragment";
    private SupportMapFragment mSupportMapFragment;

    MapView mMapView;
    public GoogleMap googleMap;


    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker mPerth;
    private Marker mSydney;
    private Marker mBrisbane;

    private List<Cinema> cinemas;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);

        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;


                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);


                // Add some markers to the map, and add a data object to each marker.
                mPerth = googleMap.addMarker(new MarkerOptions()
                        .position(PERTH)
                        .title("Perth"));
                mPerth.setTag(0);

                mSydney = googleMap.addMarker(new MarkerOptions()
                        .position(SYDNEY)
                        .title("Sydney"));
                mSydney.setTag(0);

                mBrisbane = googleMap.addMarker(new MarkerOptions()
                        .position(BRISBANE)
                        .title("Brisbane"));
                mBrisbane.setTag(0);


                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(6).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                // Set a listener for marker click.
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        // Retrieve the data from the marker.
                        Integer clickCount = (Integer) marker.getTag();

                        // Check if a click count was set, then display the click count.
                        if (clickCount != null) {
                            clickCount = clickCount + 1;
                            marker.setTag(clickCount);
                            Toast.makeText(getActivity(),
                                    marker.getTitle() +
                                            " has been clicked " + clickCount + " times.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // Return false to indicate that we have not consumed the event and that we wish
                        // for the default behavior to occur (which is for the camera to move such that the
                        // marker is centered and for the marker's info window to open, if it has one).

                        return false;
                    }


                    });
            }

        });

        return view;

    }

    public  List<Cinema> putArguments(Bundle args){
        List<Cinema> cimemas = (List<Cinema>)args.getSerializable("List");
        return cinemas;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }



}
