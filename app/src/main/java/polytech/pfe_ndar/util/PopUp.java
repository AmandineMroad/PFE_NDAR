package polytech.pfe_ndar.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.Log;

import polytech.pfe_ndar.MenuActivity;
import polytech.pfe_ndar.R;
import polytech.pfe_ndar.object.Flag;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class PopUp extends DialogFragment{
    Flag flag;
    Activity activity;

    /**
     * Return a new PopUp
     * @param flag : the flag linked to the popUp
     * @param activity : activity which called
     * @return a new PopUp instance
     */
    public static PopUp newInstance(Flag flag, Activity activity) {
        Bundle args = new Bundle();
        PopUp popUp = new PopUp();
        popUp.flag = flag;
        popUp.activity = activity;
        popUp.setArguments(args);

        return popUp;
    }

    DialogInterface.OnClickListener lis = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String tag = "lis.onClick() dit : ";
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    MapTools.setLastSeen(flag);
                    Intent intent = new Intent(activity.getApplicationContext(), MenuActivity.class);
                    intent.putExtra("menu_content", flag.getPiece().getMenuArray());
                    startActivity(intent);
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Log.w(tag, "click on negative button");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    flag.changeMarkedState(activity);
                    break;
            }
        }
    };

    public Dialog onCreateDialog(Bundle savedInstanceState){
        return  new AlertDialog.Builder(getActivity())
                .setTitle(flag.getPiece().getName())
                .setMessage(R.string.pop_up_object_message)
                .setPositiveButton(R.string.yes,lis )
                .setNegativeButton(R.string.no, lis)
                .setNeutralButton(getNeutralButtonText(), lis)
                .create();
    }


    private @StringRes int getNeutralButtonText(){
        @StringRes int id;
        if (flag.isKnown()) id = R.string.pop_up_neutral_seen;
        else if (flag.isMarked()) id = R.string.pop_up_neutral_remove;
        else id = R.string.pop_up_neutral_add;
        return id;
    }

}
