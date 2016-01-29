package polytech.pfe_ndar.object;

import android.content.res.TypedArray;
import android.support.annotation.IdRes;

import java.util.ArrayList;

import polytech.pfe_ndar.R;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class Piece{
    //TODO un identifiant unique

    private final int room;
    private final String name;
    private final String description;
    private final TypedArray menuArray;


    Piece(int roomNumber, String name, String description){
        this.room = roomNumber;
        this.name = name;
        this.description = description;
        menuArray=null;
    }

    Piece(int roomNumber, String name, String description, TypedArray menu_array){
        this.room = roomNumber;
        this.name = name;
        this.description = description;
        this.menuArray = menu_array;

    }

    public int getRoom(){
        return room;
    }
    public String getName (){return name;}
    public String getDescription(){return  description;}

    public static final int INDEX_3D = 0;
    public static final int INDEX_AUDIO = 1;
    public static final int INDEX_INFOS = 2;
    public static final int INDEX_ASSOC = 3;

    public ArrayList<Integer> getMenuArrayList (){
        ArrayList<Integer> menu = new ArrayList<>(4);
        int i;
        for (i=0; i<4; i++){
            menu.add(i,0);
        }

        int itemNumber = menuArray.length();

        for (i = 0 ; i<itemNumber; i+=2){
            switch (menuArray.getResourceId(i,0)){
                case R.string.menu_3d:
                    menu.set(INDEX_3D, menuArray.getResourceId(i+1,0));
                    break;
                case R.string.menu_audio:
                    menu.set(INDEX_AUDIO, menuArray.getResourceId(i+1,0));
                    break;
                case R.string.menu_assoc:
                    menu.set(INDEX_ASSOC, menuArray.getResourceId(i+1,0));
                    break;
                case R.string.menu_infos:
                    menu.set(INDEX_INFOS, menuArray.getResourceId(i+1,0));
                    break;
                default:
                    break;
            }
        }



        return menu;
    }

    public @IdRes int[] getMenuArray (){
        @IdRes int menu [] = {0,0,0,0};
        int i;
        int itemNumber = menuArray.length();

        for (i = 0 ; i<itemNumber; i+=2){
            switch (menuArray.getResourceId(i,0)){
                case R.string.menu_3d:
                    menu[INDEX_3D] = menuArray.getResourceId(i + 1, 0);
                    break;
                case R.string.menu_audio:
                    menu[INDEX_AUDIO] = menuArray.getResourceId(i + 1, 0);
                    break;
                case R.string.menu_assoc:
                    menu[INDEX_ASSOC] = menuArray.getResourceId(i + 1, 0);
                    break;
                case R.string.menu_infos:
                    menu[INDEX_INFOS] = menuArray.getResourceId(i + 1, 0);
                    break;
                default:
                    break;
            }
        }



        return menu;
    }


}
