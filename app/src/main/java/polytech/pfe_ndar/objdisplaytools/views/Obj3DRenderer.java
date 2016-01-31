package polytech.pfe_ndar.objdisplaytools.views;

/**
 * Created by Oirled on 26/01/2016.
 */


import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import polytech.pfe_ndar.objdisplaytools.min3d.core.Object3dContainer;
import polytech.pfe_ndar.objdisplaytools.min3d.core.RendererActivity;
import polytech.pfe_ndar.objdisplaytools.min3d.parser.IParser;
import polytech.pfe_ndar.objdisplaytools.min3d.parser.Parser;
import polytech.pfe_ndar.objdisplaytools.min3d.vos.Light;
import polytech.pfe_ndar.object.Piece;

public class Obj3DRenderer extends RendererActivity {
    //TMP pour tester
    String rawPath;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        rawPath = extras.getString("raw_path");
    }

        private Object3dContainer faceObject3D;

        float touchedX = 0;
        float touchedY = 0;
          float xAngle= 0;
    float myscale=0.7f;
            float yAngle=0;
    private ScaleGestureDetector myScaler;
        @Override
        public void initScene()//Piece currentPiece
        {
            //scene.lights().add(new Light());
           // scene.lights().add(new Light());

            Light myLight = new Light();
            myLight.diffuse.setAll(242,208,164,255);
            myLight.ambient.setAll(242,208,164,90);
            myLight.specular.setAll(164,48,63,255);
            myLight.emissive.setAll(164,48,63,255);
            myLight.position.setZ(400);
            myLight.position.setY(400);


            scene.lights().add(myLight);
            //TMP
            IParser myParser;
            if (rawPath != null)  myParser = Parser.createParser(Parser.Type.OBJ, getResources(), rawPath, true);
            else {
                myParser = Parser.createParser(Parser.Type.OBJ, getResources(), "polytech.pfe_ndar:raw/ceteredtorso_obj", true);
                Log.w("3D ERROR : ", "rawPath is null");
            }

            myParser.parse();

            faceObject3D = myParser.getParsedObject();
            faceObject3D.position().x = faceObject3D.position().y = faceObject3D.position().z = 0;
            faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = 0.7f;
        //    faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = 1.0f;

            scene.addChild(faceObject3D);


        }
    public boolean onTouchEvent(MotionEvent event)
    {
        if(myScaler!=null)
        myScaler.onTouchEvent(event);
        else{
            myScaler= new ScaleGestureDetector(this, new ScaleListener());
            myScaler.onTouchEvent(event);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touchedX = event.getX();
            touchedY = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE)
        {
            xAngle += (touchedX - event.getX())/2f;
            yAngle += (touchedY - event.getY())/2f;

            touchedX = event.getX();
            touchedY = event.getY();
        }
        return true;

    }
        @Override
        public void updateScene() {
            faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = myscale;
            faceObject3D.rotation().x = xAngle;
            faceObject3D.rotation().z = yAngle;
        }

private class ScaleListener
        extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        myscale *= detector.getScaleFactor();

        // Don't let the object get too small or too large.
        myscale = Math.max(0.1f, Math.min(myscale, 5.0f));

        //invalidate();
        return true;
    }
}
}