package polytech.pfe_ndar.object;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import polytech.pfe_ndar.util.listeners.MapOnClickListener;


/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class Room {
    private final ImageButton button; //button associated with this room on the global map
    private ArrayList<Flag> flagsSet; //set of all flags (pieces) included in this room
    private final @LayoutRes int layout_flags; //id of layout which contains the flags imageButton
    private final int number; //question : inutile au final ? accès via index
    private ImageView roomFlag; //image of marked flag for global map

    /**
     * Room constructor
     *  @param number :       the number of the room (int)
     * @param activity      : the activity which call the constructor (MapActivity)
     * @param imageButtonID : id of the button associated with this room on the global map
     * @param layoutID      : id of the layout which contains the flags imageButton on detailed map
     * @param roomFlagID    : id of the imageView of marked flag for global map
     */
    public Room(int number, Activity activity, @IdRes int imageButtonID, @LayoutRes int layoutID, @IdRes int roomFlagID) {
        this.number = number;
        this.flagsSet = new ArrayList<>();
        this.layout_flags = layoutID;
        this.button = (ImageButton) activity.findViewById(imageButtonID);
        this.roomFlag = (ImageView) activity.findViewById(roomFlagID);
        if (button != null) //TMP
            button.setOnClickListener(new MapOnClickListener(this, activity));


        //TODO init flags !
    }

    /***********************************************
     * Accessors
     ***********************************************/
    public int getNumber(){
        return number;
    }

    public ImageButton getButton() {
        return button;
    }


    public int getLayout() {
        return layout_flags;
    }

    public ArrayList<Flag> getFlagsSet() {
        return flagsSet;
    }
    public void addFlag(Flag flag) {
        flagsSet.add(flag);
    }

    public @LayoutRes int getFlagLayoutID(){
        return layout_flags;
    }

    /***********************************************
     * Flags management
     ***********************************************/
    public boolean containsMarkedFlags() {
        for (Flag flag : flagsSet) {
            if (flag.isMarked()) return true;
        }
        return false;
    }

    public void displayRoomFlag() {
        if(roomFlag!=null) {
            roomFlag.setVisibility(View.VISIBLE);
        }
    }
    public void hideRoomFlag() {
        if(roomFlag!=null) {
            roomFlag.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Set image (position and visibility) of last flag on global map, centered on the room
     * @param imageView: image of last flag (from MapTools)
     */
    public void setRoomLastFlag(ImageView imageView) { //question imageView en parametre ou via appel static à mapTools ?

        ViewGroup.MarginLayoutParams buttonLayoutParams = (ViewGroup.MarginLayoutParams) button.getLayoutParams();
        ViewGroup.MarginLayoutParams imageViewLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();

        int x = buttonLayoutParams.leftMargin + (buttonLayoutParams.width / 2) - (imageViewLayoutParams.width / 2);
        int y = buttonLayoutParams.topMargin + (buttonLayoutParams.height / 2) - (imageViewLayoutParams.height / 2);

        imageViewLayoutParams.setMargins(x, y, 0, 0);

        imageView.setVisibility(View.VISIBLE);
    }

    public final void initFlags(Activity activity, TypedArray roomContent){
        int numberOfPieces = roomContent.length();
        Resources resources = activity.getResources();
//        FrameLayout layout = (FrameLayout) View.inflate(activity.getApplicationContext(), R.layout.layout_flags_room_12, null);//FIXME layout
//        FrameLayout layout = (FrameLayout) View.inflate(activity.getApplicationContext(), layout_flags, null);
        TypedArray objectData;
//        FrameLayout layout = (FrameLayout) activity.getLayoutInflater().inflate( layout_flags, null);
        int i,j;
        Piece piece;
        Flag flag;
        Tag tag;
        ImageButton imageButton;
        //pieces/flags initialization loop
        for (i = 0; i< numberOfPieces; i++){
            //getting object description array
            objectData = resources.obtainTypedArray(roomContent.getResourceId(i, 0));
            //create piece
//            piece = new Piece(this.number, objectData.getString(1), objectData.getString(2));//TODO intégrer autres attributs de piece
//TODO propre
            piece = new Piece(this.number, objectData.getString(1), objectData.getString(2),resources.obtainTypedArray(objectData.getResourceId(3,0)));//TODO intégrer autres attributs de piece
            //imageButton = (ImageButton) layout.findViewById(objectData.getResourceId(0,0));
           // flag = new Flag(imageButton, piece, activity ); //FIXME
            flag = new Flag(objectData.getResourceId(0,0), piece, activity );
            tag = new Tag(flag , objectData.getString(3));
            flagsSet.add(flag);
        }

    }

}
