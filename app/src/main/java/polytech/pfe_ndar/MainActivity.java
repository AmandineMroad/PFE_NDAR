package polytech.pfe_ndar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import polytech.pfe_ndar.util.ScreenTools;

/*
TODO Accueil joli, add audio activity
QUESTION News ?

 */



public class MainActivity extends AppCompatActivity {
    Intent mapIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TMP récupère les dimensions de l'écran en dp
        ScreenTools.init(this);

        // Create the intent for global map activity
        mapIntent = new Intent(this, MapActivity.class);

        //TMP map launcher
        Button buttonMap = (Button) findViewById(R.id.button_home_map);
        buttonMap.setOnClickListener(buttonMapListener);

        //TMP affiche les dimensions de l'écran (en dp)
        TextView textView = (TextView) findViewById(R.id.homeTextView);
        textView.setText(" screen size : width = "
                +ScreenTools.getScreenDpWidth()+" dp   _    height = "
                + ScreenTools.getScreenDpHeight()+" dp");
    }

    /* Map launcher */
    OnClickListener buttonMapListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(mapIntent);

        }
    };




}

