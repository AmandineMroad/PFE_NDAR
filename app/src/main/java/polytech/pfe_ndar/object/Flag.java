package polytech.pfe_ndar.object;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageButton;

import polytech.pfe_ndar.R;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class Flag {
    private boolean known, marked;
    private final Piece piece;
    public View.OnClickListener listener;
    private final @IdRes int buttonID;

    /**
     * Flag constructor
     * @param buttonID : ID of the ImageButton associated to the flag
     * @param piece : Piece flag refers to
     */
    public Flag(@IdRes int buttonID, Piece piece) {
        this.piece = piece;
        this.buttonID = buttonID;
        known = marked = false;
    }

    /***********************************************
     * Accessors
     ***********************************************/
    public boolean isKnown(){return known;}
    public boolean isMarked(){return marked;}
    public int getRoomNumber(){return piece.getRoom();}
    public Piece getPiece(){return piece;}
    public @IdRes int getButtonID(){return buttonID;}


    /***********************************************
     * Flag state management
     ***********************************************/
    private static final int DEFAULT_FLAG = R.drawable.flag_default;
    private static final int SEEN_FLAG = R.drawable.flag_green;
    private static final int MARKED_FLAG = R.drawable.flag_red;
    private static final int LAST_FLAG = R.drawable.flag_last_seen;

    public void setToLast(Activity activity){
        known = true;
        ImageButton button = (ImageButton) activity.findViewById(buttonID);
        button.setImageResource(LAST_FLAG);
    }

    public void setToSeen(Activity activity){
        ImageButton button = (ImageButton) activity.findViewById(buttonID);
        button.setImageResource(SEEN_FLAG);
    }

    public void changeMarkedState(Activity activity){
        if (!known){
            ImageButton button = (ImageButton) activity.findViewById(buttonID);
            if (marked){
               marked =false;
                button.setImageResource(DEFAULT_FLAG);
            } else {
                marked = true;
                button.setImageResource(MARKED_FLAG);
            }
        }
    }

}
