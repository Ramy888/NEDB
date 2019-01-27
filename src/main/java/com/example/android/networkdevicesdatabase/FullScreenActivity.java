package com.example.android.networkdevicesdatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.android.networkdevicesdatabase.Data.FullScreenImageAdapter;

import java.util.ArrayList;

public class FullScreenActivity extends Activity {

    private FullScreenImageAdapter adapter;
    private ViewPager viewPager;
    private ArrayList<String> photos = new ArrayList<>();
    private ImageView mTopologyIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        viewPager = (ViewPager) findViewById(R.id.pager);
        mTopologyIV = (ImageView) findViewById(R.id.imgDisplay);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity!=null) {

            int position = intentThatStartedThisActivity.getIntExtra("position", 0);
            if (intentThatStartedThisActivity.hasExtra("topologyImage")) {
                photos.add(intentThatStartedThisActivity.getStringExtra("topologyImage"));
            }

        adapter = new FullScreenImageAdapter(FullScreenActivity.this,
                photos);

        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);

        }
    }
}
