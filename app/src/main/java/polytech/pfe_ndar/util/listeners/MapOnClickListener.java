package polytech.pfe_ndar.util.listeners;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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

    public MapOnClickListener(Room room, Activity activity){
        this.room = room;
        this.activity = activity;
    }


    @Override
    public void onClick(View v) {
        activity.setContentView(R.layout.map_room);
        ImageView imageView = (ImageView) activity.findViewById(R.id.map_room_imageView);
        imageView.setImageResource(room.getDrawable());

        //Display flags layout
        MapTools.displayFlagLayout(room.getNumber());
        Log.w("MAP OnClick Listener", "On click end");
        //TODO setFlags()


    }
}
