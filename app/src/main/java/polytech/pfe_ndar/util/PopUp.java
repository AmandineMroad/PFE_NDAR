package polytech.pfe_ndar.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import polytech.pfe_ndar.R;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class PopUp extends DialogFragment{
    String titleText;
    String messageText;
   /* String buttonPositiveText;
    String buttonNegativeText;
    String buttonNeutralText;*/


    public static PopUp newInstance(String title, String message) {

        Bundle args = new Bundle();

        PopUp fragment = new PopUp();
        fragment.titleText = title;
        fragment.messageText = message;
        fragment.setArguments(args);
        return fragment;
    }


    DialogInterface.OnClickListener lis = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            String tag = "lis.onClick() dit : ";
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    Log.i(tag, "click on positive button");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Log.i(tag, "click on negative button");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    Log.i(tag, "click on neutral button");
                    break;
            }
        }
    };





    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity())
               /* .setMessage("Boite de dialogue super swaggy ! ")*/
                .setTitle(titleText)
                .setMessage(messageText)
                .setPositiveButton(R.string.yes,lis )
                .setNegativeButton(R.string.no, lis)
                .setNeutralButton(R.string.fav_add, lis)
                .create();
    }



}
