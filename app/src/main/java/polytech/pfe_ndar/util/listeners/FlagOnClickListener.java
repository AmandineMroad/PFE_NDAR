package polytech.pfe_ndar.util.listeners;

import android.app.Activity;
import android.view.View;

import polytech.pfe_ndar.object.Flag;
import polytech.pfe_ndar.util.PopUp;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class FlagOnClickListener implements View.OnClickListener {

    private Flag flag;
    private Activity activity;
    private PopUp popUp;

    public FlagOnClickListener(Flag flag, Activity activity){
        this.flag = flag;
        this.activity = activity;
        this.popUp = PopUp.newInstance(flag);
    }

    @Override
    public void onClick(View v) {
        popUp.show(activity.getFragmentManager(),"");
    }

}
