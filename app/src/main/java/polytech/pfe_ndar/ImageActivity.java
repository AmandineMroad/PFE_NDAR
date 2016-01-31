package polytech.pfe_ndar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.v4.view.ViewPager;

import polytech.pfe_ndar.menu.ImageSwiper;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class ImageActivity extends Activity {
    ViewPager viewPager;
    ImageSwiper imageSwiper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        Bundle extras = getIntent().getExtras();
        @ArrayRes int arrayRes = extras.getInt("array_resId") ;
        imageSwiper = new ImageSwiper(this, arrayRes);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(imageSwiper);
    }
}
