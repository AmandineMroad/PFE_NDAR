package polytech.pfe_ndar.objdisplaytools.views;

/**
 * Created by Oirled on 26/01/2016.
 */


import polytech.pfe_ndar.objdisplaytools.min3d.core.Object3dContainer;
import polytech.pfe_ndar.objdisplaytools.min3d.core.RendererActivity;
import polytech.pfe_ndar.objdisplaytools.min3d.parser.IParser;
import polytech.pfe_ndar.objdisplaytools.min3d.parser.Parser;
import polytech.pfe_ndar.objdisplaytools.min3d.vos.Light;

public class Obj3DRenderer extends RendererActivity {

        private Object3dContainer faceObject3D;

        /** Called when the activity is first created. */
        @Override
        public void initScene()
        {
            scene.lights().add(new Light());
            scene.lights().add(new Light());

            Light myLight = new Light();
            myLight.position.setZ(150);
            scene.lights().add(myLight);

            IParser myParser = Parser.createParser(Parser.Type.OBJ, getResources(), "com.demo.oirled.delph3dviewer:raw/face_obj", true);
            myParser.parse();

            faceObject3D = myParser.getParsedObject();
            faceObject3D.position().x = faceObject3D.position().y = faceObject3D.position().z = 0;
            faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = 1.0f;
// Depending on the model you will need to change the scale
            faceObject3D.scale().x = faceObject3D.scale().y = faceObject3D.scale().z = 1.0f;

            scene.addChild(faceObject3D);

        }

        @Override
        public void updateScene() {
            faceObject3D.rotation().x += 0.5;
            faceObject3D.rotation().z += 1;
        }
}
