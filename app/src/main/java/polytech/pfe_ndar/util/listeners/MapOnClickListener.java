package polytech.pfe_ndar.util.listeners;

import android.app.Activity;
import android.util.Log;
import android.view.View;

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

        if (first) {
            first = false; //TODO verifier nbre d'appel
            MapTools.initDetailledRoom(room.getNumber());

        }

        //TODO set flags
        Log.w("MapListener", "on click performed");

    }
}
