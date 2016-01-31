package polytech.pfe_ndar.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.ArrayRes;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import polytech.pfe_ndar.R;

/**
 * PFE_NDAR _ Polytech Marseille _ 2016
 * Nicolas DELRIO, Amandine ROGER, IRM 2016
 */
public class ImageSwiper extends PagerAdapter {
    private int[] images_resources ;
    private Context context;
    private LayoutInflater layoutInflater;


    public ImageSwiper(Context context, @ArrayRes int arrayResId){
        this.context = context;
        Resources resources = context.getResources();
        TypedArray array = resources.obtainTypedArray(arrayResId);

        int imagesCount = array.length();
        images_resources = new int[imagesCount];
        for (int i =0; i<imagesCount; i++){
            images_resources[i] = array.getResourceId(i, 0);
        }
    }

    @Override
    public int getCount() {
        return images_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        ImageView imageView = (ImageView)item_view.findViewById(R.id.image_view);
        imageView.setImageResource(images_resources[position]);
        TextView textView =(TextView) item_view.findViewById(R.id.text_view);
        textView.setText("Image : "+(position+1));
        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
