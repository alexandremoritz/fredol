package alexandremoritz.com.fredol;

/**
 * Created by morit on 26/11/2017.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by morit on 26/11/2017.
 */

public class InfoFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "InfoFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment,container,false);


        return view;
    }
}
