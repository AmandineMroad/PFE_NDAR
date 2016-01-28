package polytech.pfe_ndar.objdisplaytools.views;

/**
 * Created by Oirled on 26/01/2016.
 */


import android.view.MotionEvent;

import polytech.pfe_ndar.objdisplaytools.min3d.core.Object3dContainer;
import polytech.pfe_ndar.objdisplaytools.min3d.core.RendererActivity;
import polytech.pfe_ndar.objdisplaytools.min3d.parser.IParser;
import polytech.pfe_ndar.objdisplaytools.min3d.parser.Parser;
import polytech.pfe_ndar.objdisplaytools.min3d.vos.Light;

public class Obj3DRenderer extends RendererActivity {

        private Object3dContainer faceObject3D;
        float touchedX = 0;
        float touchedY = 0;
          float xAngle= 0;
            float yAngle=0;
        /** Called when the activity is first created. */
        @Override
        public void initScene()
        {
            //scene.lights().add(new Light());
           // scene.lights().add(new Light());

            Light myLight = new Light();
            myLight.diffuse.setAll(10,100,120,100);
            myLight.ambient.setAll(0,0,0,0);
            myLight.specular.setAll(0,0,0,0);
            myLight.emissive.setAll(0, 0, 0, 0);
            myLight.position.setY(-400);

            scene.lights().add(myLight);

            IParser myParser = Parser.createParser(Parser.Type.OBJ, getResources(), "polytech.pfe_ndar:raw/ceteredtorso_obj", true);
            myParser.parse();

            faceObject3D = myParser.getParsedObject();
            faceObject3D.position().x = faceObject3D.position().y = faceObject3D.position().z = 0;
            faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = 0.7f;
// Depending on the model you will need to change the scale
        //    faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = 1.0f;

            scene.addChild(faceObject3D);

        }
    public boolean onTouchEvent(MotionEvent event)
    {
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
            faceObject3D.rotation().x = xAngle;
            faceObject3D.rotation().z = yAngle;
        }
}
