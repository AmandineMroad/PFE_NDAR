package polytech.pfe_ndar.object;

import android.app.Activity;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import polytech.pfe_ndar.util.MapTools;
import polytech.pfe_ndar.util.listeners.MapOnClickListener;


/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class Room {
    private final ImageButton button; //button associated with this room on the global map
    private ArrayList<Flag> flagsSet; //set of all flags (pieces) included in this room
    private final int drawable; //image displayed in detailed map
    private final int number; //question : inutile au final ? accès via index
    private ImageView roomFlag; //image of marked flag for global map

    /**
     * Room constructor
     *
     * @param number:       the number of the room (int)
     * @param activity      : the activity which call the constructor (MapActivity)
     * @param imageButtonID : id of the button associated with this room on the global map
     * @param drawableResID : id of the image displayed in detailed map
     * @param roomFlagID    : id of the image of marked flag for global map
     */
    public Room(int number, Activity activity, @IdRes int imageButtonID, @DrawableRes int drawableResID, @IdRes int roomFlagID) {
        this.number = number;
        this.flagsSet = new ArrayList<>();
        this.drawable = drawableResID;
        this.button = (ImageButton) activity.findViewById(imageButtonID);
        this.roomFlag = (ImageView) activity.findViewById(roomFlagID);
        button.setOnClickListener(new MapOnClickListener(this, activity));

        //TMP
        Flag tmp = new Flag(null, new Piece(12));
        flagsSet.add(tmp);
        MapTools.setLastSeen(tmp);
        //TMP fin
        //TODO init flags !
    }

    /***********************************************
     * Accessors
     ***********************************************/
    public ImageButton getButton() {
        return button;
    }

    public int getDrawable() {
        return drawable;
    }

    public ArrayList<Flag> getFlagsSet() {
        return flagsSet;
    }
    public void addFlag(Flag flag) {
        flagsSet.add(flag);
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
        roomFlag.setVisibility(View.VISIBLE);
    }
    public void hideRoomFlag() {
        roomFlag.setVisibility(View.INVISIBLE);
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

}