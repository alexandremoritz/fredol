package alexandremoritz.com.fredol;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by morit on 27/11/2017.
 */

public class CinemaAdapter extends ArrayAdapter<Cinema> {


    List<Cinema> cinemas;



    //cinemas est la liste des models à afficher
    public CinemaAdapter(Context context, List<Cinema> cinemas) {
        super(context, 0, cinemas);
        this.cinemas=cinemas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.container_cine,parent, false);
        }

        CinemaViewHolder viewHolder = (CinemaViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CinemaViewHolder();
            viewHolder.enseigne = (TextView) convertView.findViewById(R.id.enseigne);
            viewHolder.adresse = (TextView) convertView.findViewById(R.id.adresse);
            viewHolder.icone = (ImageView) convertView.findViewById(R.id.icone);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Cinema> cinemas
        Cinema cinema = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.enseigne.setText(cinema.getFields().getNomEtablissement());
        viewHolder.adresse.setText(cinema.getFields().getAdresse());
        viewHolder.icone.setImageResource(R.drawable.ic_cine);

        return convertView;
    }

    public void notifyDataChanged() {
    }

    private class CinemaViewHolder{
        public TextView enseigne;
        public TextView adresse;
        public ImageView icone;
    }


}
