package com.example.android.networkdevicesdatabase.Data;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.android.networkdevicesdatabase.Helpers.TouchImageView;
import com.example.android.networkdevicesdatabase.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rami.magdy on 27/04/2018.
 */

public class FullScreenImageAdapter extends PagerAdapter {

    private Activity _activity;
    private ArrayList<String> photos;
    private LayoutInflater inflater;
    private TouchImageView imgDisplay;
    private Button btnClose;
    private Context context;

    // constructor
    public FullScreenImageAdapter(Activity activity,
                                  ArrayList<String> imagePaths) {
        this._activity = activity;
        this.photos = imagePaths;
    }

    @Override
    public int getCount() {
        return this.photos.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);


        ((ViewPager) container).addView(viewLayout);
        imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);


        // close button click event
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });

        Picasso.with(context)
                .load(photos.get(position))
                //.placeholder(R.drawable.close_button)
                .fit()
                .into(imgDisplay);


        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
    }
}
