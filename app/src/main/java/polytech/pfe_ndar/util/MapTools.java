package polytech.pfe_ndar.util;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
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
    public static final int NUMBER_OF_ROOMS = 13;

    //QUESTION Stocker ici le tableau des "à voir" ?

    private static Flag lastSeen = null;
    private static ArrayList<Room> rooms;
    private static Activity mapActivity;

    private static ImageView roomLastFlag;


    /***********************************************
     * Accessors
     ***********************************************/
    public static void setLastSeen(Flag flag) {
        if (lastSeen != null) {
            lastSeen.setToSeen();
        }

        lastSeen = flag;
        lastSeen.setToLast();
    }

    /***********************************************
     * Initialization functions
     ***********************************************/
    /**
     * Initialize MapTools, set activity and roomLastFlag; declare rooms and initialize them with their flags/pieces
     * @param activity : mapActivity
     */

    public static void initMap(Activity activity) {

        mapActivity = activity;
        rooms = new ArrayList<>(13);

        roomLastFlag = (ImageView) activity.findViewById(R.id.map_global_flag_last);
        roomLastFlag.setVisibility(View.INVISIBLE);

        initRooms();
    }

    /**
     * Create rooms and their content and store them
     */
    private static final int IMAGE_BUTTON_INDEX = 0;
    private static final int DRAWABLE_INDEX = 1;
    private static final int LAYOUT_INDEX = 2;
    private static final int GLOBAL_FLAG_INDEX = 3 ;
    private static final int ROOM_CONTENT_INDEX = 4;

    private static final void initRooms() {
        //Get the construction typed array
        Resources resources = mapActivity.getResources();
        TypedArray constructionData = resources.obtainTypedArray(R.array.map_construction);

        TypedArray roomData;
        Room room = null;

        //Rooms creation loop
        for (int i = 0; i < NUMBER_OF_ROOMS; i++) {
            //Get room_description typed array
            roomData = resources.obtainTypedArray(constructionData.getResourceId(i, 0));
            //TMP if tmp à supprimer
            if(roomData.length() > 0) {
                //Create room
                room = new Room(i + 1, mapActivity, roomData.getResourceId(IMAGE_BUTTON_INDEX, 0), roomData.getResourceId(DRAWABLE_INDEX, 0),
                        roomData.getResourceId(LAYOUT_INDEX, 0),roomData.getResourceId(GLOBAL_FLAG_INDEX, 0) );
                //Add all flags
                room.initFlags(mapActivity, resources.obtainTypedArray(roomData.getResourceId(ROOM_CONTENT_INDEX, 0)));
            } else {
                room = null;
            }
            //Store room
            rooms.add(i, room);
        }
    }


    /***********************************************
     * Global map management
     ***********************************************/

    /**
     * Display flags on global map on rooms in which there is a marked piece or the last seen piece
     */
    public static void setRoomsGlobalFlags() {
        //FIXME
        for (Room room : rooms) {
            if (room != null) {//TMP
                if (room.containsMarkedFlags()) {
                    room.displayRoomFlag();
                } else {
                    room.hideRoomFlag();
                }
            }
        }

        //Setting last flag
        if (lastSeen != null) rooms.get(lastSeen.getRoomNumber() - 1).setRoomLastFlag(roomLastFlag);

    }

    /***********************************************
     * Detailed map management
     ***********************************************/
    public static void hideAllFlagsLayout(){
        FrameLayout layout;
        for (Room room : rooms){
            if ((room != null) && (room.getFlagLayoutID() != 0)  ) {//TMP
                layout = (FrameLayout) mapActivity.findViewById(room.getFlagLayoutID());
                layout.setVisibility(View.INVISIBLE);
            }
        }
    }

    public static void displayFlagLayout(int roomNumber){
        hideAllFlagsLayout();
        //rooms.get(roomNumber-1).getFlagLayout().setVisibility(View.VISIBLE);
        FrameLayout layout = (FrameLayout) mapActivity.findViewById(rooms.get(roomNumber - 1).getFlagLayoutID());
        layout.setVisibility(View.VISIBLE);

        Log.w("DISPLAY FLAG LAYOUT", "passed !"); //TMP
    }
    public static void  hideFlagLayout(int roomNumber){
        FrameLayout layout = (FrameLayout) mapActivity.findViewById(rooms.get(roomNumber-1).getFlagLayoutID());
        layout.setVisibility(View.INVISIBLE);
    }

    /*****************************************************************************/
//TMP déplacer ? dans mapActivity peut etre ?
    public static void showPopUp(Activity activity, String dialogMessage) { //FIXME
        DialogFragment dialogFragment = PopUp.newInstance("FROM MAP", dialogMessage);
        dialogFragment.show(activity.getFragmentManager(), "FROM MAP");
    }



}
