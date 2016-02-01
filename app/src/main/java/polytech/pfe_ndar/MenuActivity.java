package polytech.pfe_ndar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import polytech.pfe_ndar.objdisplaytools.views.Obj3DRenderer;
import polytech.pfe_ndar.object.Piece;
import polytech.pfe_ndar.util.listeners.MenuOnClickListener;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class MenuActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        Bundle extras = getIntent().getExtras();
        @IdRes int menu[] = extras.getIntArray("menu_content") ;
        Intent intent;
        String s;
        ImageButton button;
            for(int i=0; i<4; i++){
            if(menu[i] != 0){
                switch (i){
                    case Piece.INDEX_3D:
                        s = getString(menu[i]);
                        intent = new Intent(getApplicationContext(), Obj3DRenderer.class);
                        intent.putExtra("raw_path", s);
                        button = (ImageButton) findViewById(R.id.menu_button_3d);
                        button.setOnClickListener(new MenuOnClickListener(this, intent));
                        break;
                    case Piece.INDEX_AUDIO:
                        s = getString(menu[i]);
                        intent = new Intent(getApplicationContext(), AudioActivity.class);
                        intent.putExtra("raw_name", s);
                        button = (ImageButton) findViewById(R.id.menu_button_son);
                        button.setOnClickListener(new MenuOnClickListener(this, intent));
                        break;
                    case Piece.INDEX_INFOS:
                        s = getString(menu[i]);
                        intent = new Intent(getApplicationContext(), TextActivity.class);
                        intent.putExtra("raw_path", s);
                        button = (ImageButton) findViewById(R.id.menu_button_infos);
                        button.setOnClickListener(new MenuOnClickListener(this, intent));
                        break;
                    case Piece.INDEX_IMAGES:
                        @ArrayRes int arrayRes = menu[i];
                        intent = new Intent(getApplicationContext(), ImageActivity.class);
                        intent.putExtra("array_resId", arrayRes);
                        button = (ImageButton) findViewById(R.id.menu_button_images);
                        button.setOnClickListener(new MenuOnClickListener(this,intent));
                        break;
                }

            }
        }


    }
}
