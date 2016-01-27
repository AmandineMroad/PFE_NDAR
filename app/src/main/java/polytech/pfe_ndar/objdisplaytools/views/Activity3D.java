package polytech.pfe_ndar.objdisplaytools.views;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
public class Activity3D extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.startActivity( new Intent(this,Obj3DRenderer.class));
    }
}