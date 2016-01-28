package polytech.pfe_ndar.objdisplaytools.views;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;


import java.io.Console;

import polytech.pfe_ndar.R;
import polytech.pfe_ndar.object.Piece;

public class Activity3D extends AppCompatActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Log.d("TTEST", "test");
        Toolbar toolbar = new Toolbar(this);
        toolbar.setTitle("Test");
        setSupportActionBar(toolbar);
        this.startActivity( new Intent(this,Obj3DRenderer.class));
    }

}