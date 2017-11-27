package alexandremoritz.com.fredol;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morit on 26/11/2017.
 */

public class ListFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "ListFragment";

    private ListView mListView;
    private List<Cinema> cinemas;


    private List<Cinema> genererCinemas(){
        List<Cinema> cinemas = new ArrayList<Cinema>();
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));
        cinemas.add(new Cinema("Test", "3 rue de la chouette"));


        for (Cinema cine : cinemas) {
            Log.i(TAG, cine.getDatasetid() + ": " + cine.getRecordid());
        }

        return cinemas;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment,container,false);


        //mListView = (ListView) view.findViewById(R.id.listView);

        //List<Cinema> cinemas = genererCinemas();
        //CinemaAdapter adapter = new CinemaAdapter(getActivity(),cinemas);

        //mListView.setAdapter(adapter);


        return view;
    }

    public static List<Cinema> putArguments(Bundle args){
        List<Cinema> cimemas = (List<Cinema>)args.getSerializable("List");
        return cimemas;
    }

}
