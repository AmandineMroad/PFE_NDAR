package polytech.pfe_ndar.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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

    public static PopUp newInstance(Flag flag) {
        Bundle args = new Bundle();
        PopUp popUp = new PopUp();
        popUp.flag = flag;
        popUp.setArguments(args);

        return popUp;
    }

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
                    Log.w(tag, "click on positive button");
                    //TODO get menu from flag.piece
                    //TODO display menu
                    Intent intent = new Intent(activity.getApplicationContext(), MenuActivity.class);
//                    intent.putIntegerArrayListExtra("menu_content", flag.getPiece().getMenuArrayList());
                    intent.putExtra("menu_content", flag.getPiece().getMenuArray());
                    Log.i("pop up", "breakpoint");
                    startActivity(intent);

                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    Log.w(tag, "click on negative button");
                    break;
                case DialogInterface.BUTTON_NEUTRAL:
                    Log.w(tag, "click on neutral button");
                    break;
            }
        }
    };





    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog dialog =  new AlertDialog.Builder(getActivity())
               /* .setMessage("Boite de dialogue super swaggy ! ")*/
                .setTitle(flag.getPiece().getName())
                .setMessage(R.string.pop_up_object_message)
                .setPositiveButton(R.string.yes,lis )
                .setNegativeButton(R.string.no, lis)
                .setNeutralButton(R.string.pop_up_neutral_add, lis)
                .create();

        setNeutralButtonContent(dialog);

        return dialog;

    }


    public void setNeutralButtonContent(Dialog popup){
        Button button = ((AlertDialog) popup).getButton(DialogInterface.BUTTON_NEUTRAL);
        if (flag.isKnown()) {
            button.setText(R.string.pop_up_neutral_seen);
            button.setClickable(false);
        } else if (flag.isMarked()){
            button.setText(R.string.pop_up_neutral_remove);
        }
    }

}
