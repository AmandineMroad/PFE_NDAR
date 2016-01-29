package polytech.pfe_ndar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

import polytech.pfe_ndar.objdisplaytools.views.Obj3DRenderer;
import polytech.pfe_ndar.util.ScreenTools;

/*
TODO Accueil joli, add audio activity
QUESTION News ?

 */



public class MainActivity extends AppCompatActivity {
    Intent mapIntent;
    Intent intent3d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TMP récupère les dimensions de l'écran en dp
        ScreenTools.init(this);
        // Create the intent for global map activity
        //startActivity(new Intent(this, NfcActivity.class));
        mapIntent = new Intent(this, MapActivity.class);
        intent3d = new Intent(this, Obj3DRenderer.class);
        //TMP map launcher
        Button buttonMap = (Button) findViewById(R.id.button_home_map);
        buttonMap.setOnClickListener(buttonMapListener);

        Button button3D = (Button) findViewById(R.id.button3D);
        button3D.setOnClickListener(but3dLis);

        //TMP affiche les dimensions de l'écran (en dp)
        TextView textView = (TextView) findViewById(R.id.homeTextView);
        textView.setText(" screen size : width = "
                + ScreenTools.getScreenDpWidth() + " dp   _    height = "
                + ScreenTools.getScreenDpHeight() + " dp");
    }
    /* Map launcher */
    OnClickListener buttonMapListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(mapIntent);

        }
    };


    OnClickListener but3dLis = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(intent3d);
        }
    };


}

