package polytech.pfe_ndar.object;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Oirled on 29/01/2016.
 */
public class Tag {
    static public ArrayList<Tag> tagSet;
    static boolean OneInitControl;
    public Flag tagFlag;
    public String tagID;
    public Tag(Flag flag , String tag){
        tagFlag=flag;
        tagID=tag;
        Log.d("AddTag", "New tag id="+tagID);
        tagSet.add(this);
    }


    public static void initStatic() {
        if (!OneInitControl) {
            Log.d("ONEINITCONTROL", "TRUE");

            tagSet = new ArrayList<>();
            OneInitControl = true;
        } else {
            Log.d("ONEINITCONTROL", "FALSE");
        }
    }
}
