package polytech.pfe_ndar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;

import java.util.ArrayList;

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
        ArrayList<Integer> menu_content = extras.getIntegerArrayList("menu_content");
        @IdRes int menu[] = extras.getIntArray("menu_cont") ;
        Intent intent;
        String s;
        @IdRes int tmp;
        ImageButton button;
            for(int i=0; i<4; i++){
              //  tmp = menu_content.get(i);
                tmp = menu[i];
            if(tmp != 0){
                switch (i){
                    case Piece.INDEX_3D:
                         s = getString(menu[i]);
                        intent = new Intent(getApplicationContext(), Obj3DRenderer.class);
                        intent.putExtra("raw_path", s);
                        button = (ImageButton) findViewById(R.id.menu_button_3d);
                        button.setOnClickListener(new MenuOnClickListener(this, intent));
                        break;
                    case Piece.INDEX_AUDIO:

                        break;
                    case Piece.INDEX_INFOS:
                        s = getString(menu[i]);
                        intent = new Intent(getApplicationContext(), TextActivity.class);
                        intent.putExtra("raw_path", s);
                        button = (ImageButton) findViewById(R.id.menu_button_infos);
                        button.setOnClickListener(new MenuOnClickListener(this, intent));
                        break;
                    case Piece.INDEX_ASSOC:
                        break;
                }

            }
        }


    }
}
