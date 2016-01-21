package polytech.pfe_ndar.util;

import android.app.Activity;
import android.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import polytech.pfe_ndar.R;
import polytech.pfe_ndar.object.Flag;
import polytech.pfe_ndar.object.Room;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class MapTools {

    //QUESTION Stocker ici le dernier objet vu ?
    //QUESTION Stocker ici le tableau des "à voir" ?

    private static Flag lastSeen = null;
    private static ArrayList<Room> rooms;
    private static Activity mapActivity;

    private static ImageView roomLastFlag;

    public static void setLastSeen(Flag flag){
        if (lastSeen != null){
            lastSeen.setToSeen();
            /*int room = lastSeen.getPiece().getRoom();
            if(!isRoomMarked(room)){//FIXME
                roomMarked.remove(room);
            }*/
        }

        lastSeen = flag;
        lastSeen.setToLast();
    }



    /*****************************************************************************/

    public static void showPopUp(Activity activity, String dialogMessage){ //FIXME
        DialogFragment dialogFragment= PopUp.newInstance("FROM MAP", dialogMessage);
        dialogFragment.show(activity.getFragmentManager(), "FROM MAP");
    }


    public static void initMap(Activity activity){
        mapActivity = activity;
        rooms = new ArrayList<>(13);
        roomLastFlag = (ImageView) activity.findViewById(R.id.map_global_flag_last);
        roomLastFlag.setVisibility(View.INVISIBLE);
        Room room = new Room(12, activity, R.id.room12, R.drawable.room12, R.id.map_global_flag12); //QUESTION Stockage des rooms ?

        //TMP declaration des salles
        for(int i=0; i<11; i++){
            rooms.add(i, null);
        }
        rooms.add(11, room);

    }


    public static void setRoomsFlags(){
        //FIXME
        for (Room room : rooms){
            if (room != null) {//TMP
            if (room.containsMarkedFlags()){
                room.displayRoomFlag();
            } else {
                room.hideRoomFlag();
            }}
        }

        //Setting last flag
       if (lastSeen != null) rooms.get( lastSeen.getRoomNumber() - 1 ).setRoomLastFlag(roomLastFlag);

    }
}
