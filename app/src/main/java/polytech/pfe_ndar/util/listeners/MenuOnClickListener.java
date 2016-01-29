package polytech.pfe_ndar.util.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class MenuOnClickListener implements View.OnClickListener {

    Activity activity;
    Intent intent;
    String rawPath; //for 3D

    public MenuOnClickListener(Activity activity, Intent intent){
        this.activity = activity;
        this.intent = intent;
    }

    @Override
    public void onClick(View v) {
        activity.startActivity(intent);
    }
}
