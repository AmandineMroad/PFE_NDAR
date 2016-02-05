package polytech.pfe_ndar.util.listeners;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.os.Bundle;

import polytech.pfe_ndar.MapActivity;
import polytech.pfe_ndar.R;
import polytech.pfe_ndar.object.Room;
import polytech.pfe_ndar.util.MapTools;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class MapOnClickListener implements View.OnClickListener {
    Room room;
    Activity activity;
    boolean first;

    public MapOnClickListener(Room room, Activity activity){
        this.room = room;
        this.activity = activity;
        first = true;

    }


    @Override
    public void onClick(View v) {
        activity.setContentView(room.getLayout());
        ((MapActivity)activity).mapmode=true;

        if (first) {
            first = false; //TODO verifier nbre d'appel
            Log.d("ALERTE", "MULTIPLES APPELS");
            MapTools.initDetailledRoom(room.getNumber());

        }

        //TODO set flags
        Log.w("MapListener", "on click performed");

    }

}
