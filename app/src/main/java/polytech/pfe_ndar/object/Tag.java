package polytech.pfe_ndar.object;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Oirled on 29/01/2016.
 */
public class Tag {
    static ArrayList<Tag> tagSet;
    static boolean OneInitControl;
    private Flag tagFlag;
    private String tagID;
    public Tag(Flag flag , String tag){
        tagFlag=flag;
        tagID=tag;
        tagSet.add(this);
    }


    public static void initStatic() {
        if (OneInitControl) {
            Log.d("ONEINITCONTROL", "TRUE");

            tagSet = new ArrayList<>();
            OneInitControl = false;
        } else {
            Log.d("ONEINITCONTROL", "FALSE");
        }
    }
}
