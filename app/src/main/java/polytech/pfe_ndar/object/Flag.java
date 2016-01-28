package polytech.pfe_ndar.object;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import polytech.pfe_ndar.R;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class Flag {
    private static final int DEFAULT_FLAG = R.drawable.flag_default;
    private static final int SEEN_FLAG = R.drawable.flag_green;
    private static final int MARKED_FLAG = R.drawable.flag_red;
    private static final int LAST_FLAG = R.drawable.flag_last_seen;

    private boolean known, marked;
    private final ImageButton button;
    private final Piece piece;

    public View.OnClickListener listener;

    //TMP
    private final @IdRes int buttonID;

    /**
     * Flag constructor
     * @param button : ImageButton associated to the flag
     * @param piece : Piece flag refers to
     * @param activity
     */
    public Flag(ImageButton button, Piece piece, Activity activity) {
        this.button = button;
        this.piece = piece;

        known = marked = false;

      //  FlagOnClickListener listener = new FlagOnClickListener(this, activity);
      //  this.button.setOnClickListener(listener);
        Log.i("Flag constructor", "listener attached !");
        buttonID = 0;
    }
    //TODO mettre au propre!
    public Flag(@IdRes int buttonID, Piece piece, Activity activity) {
        this.button = null;
        this.piece = piece;

        known = marked = false;

        this.buttonID = buttonID;

    }

    /***********************************************
     * Accessors
     ***********************************************/
    public boolean isKnown(){return known;}
    public boolean isMarked(){return marked;}
    public int getRoomNumber(){return piece.getRoom();}
    public Piece getPiece(){return piece;}
    public ImageButton getButton(){return button;}
    public int getButtonID(){return buttonID;}

    public void setToLast(){
        known = true;
        button.setImageResource(LAST_FLAG);
    }
    public void setToSeen(){button.setImageResource(SEEN_FLAG);}

    public void changeMarkedState(){
        if (!known){
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
