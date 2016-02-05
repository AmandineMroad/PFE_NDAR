package polytech.pfe_ndar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import polytech.pfe_ndar.util.MapTools;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class MapActivity extends AppCompatActivity {
//TODO redimensionner les images des boutons (map) et image pour plan détaillé
    public boolean mapmode;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_global);
        mapmode=false;
        //map initialization
        MapTools.initMap(this);

    }

    protected void onResume(){
        super.onResume();
        MapTools.setRoomsGlobalFlags();
    }

//    @Override
//    public     void onBackPressed() {
//        if(mapmode){
//            setContentView(R.layout.map_global);
//            //MapTools.initMap(this);
//            //MapTools.setRoomsGlobalFlags();
//            mapmode=false;
//        }else {
//            super.onBackPressed();
//        }
//    }

}

