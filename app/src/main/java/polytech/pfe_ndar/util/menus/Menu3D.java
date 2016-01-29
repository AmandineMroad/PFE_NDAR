package polytech.pfe_ndar.util.menus;

import android.util.Log;
import android.view.View;

import polytech.pfe_ndar.R;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */

public class Menu3D extends MenuItem {
    private final static String TAG = " _ Menu3D _ ";
    String objRaw;
    View.OnClickListener listener;


    Menu3D(String obj_raw/*@IdRes int buttonID*/){
        this.texte = "3D";
        this.icon = R.drawable.menu_icon_3d;
        this.objRaw = obj_raw;
//        this.buttonID = buttonID;
        this.listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start activity with objraw as parameter
                Log.w(TAG, "click on 3d menu item, launch renderer activity");
            }
        };
    }

    public View.OnClickListener getListener(){return listener;}
    public String getObjRaw(){return objRaw;}

}
