package com.example.android.networkdevicesdatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.networkdevicesdatabase.Data.Device;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {

    TextView mDeviceName, mDeviceType, mDeviceInfo_1, mDeviceInfo_2, mDeviceInfo_3, mDeviceInfo_4, summary;
    ImageView mDeviceImage;
    public static String deviceName = "deviceName";
    public static String videoUrl = "videoUrl";
    public static String pdfUrl = "pdfUrl";
    public static String deviceInfo_1 = "deviceInfo_1";
    public static String deviceInfo_2 = "deviceInfo_2";
    public static String deviceInfo_3 = "deviceInfo_3";
    public static String deviceInfo_4 = "deviceInfo_4";
    private Context mConte = this;
    private Device device;


    @BindView(R.id.prepare_video)
    Button prepareVideo;
    @BindView(R.id.prepare_pdf)
    Button preparePdf;

    public static List<String> modelList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detai);

        ButterKnife.bind(this);

        Intent intentThatStartedThisActivity = getIntent();

        mDeviceType = (TextView) findViewById(R.id.device_typ);
        mDeviceName = (TextView) findViewById(R.id.device_name);
        mDeviceImage = (ImageView) findViewById(R.id.detail_image_view);
        mDeviceInfo_1 = (TextView) findViewById(R.id.detail_overview_1);
        mDeviceInfo_2 = (TextView) findViewById(R.id.detail_overview_2);
        mDeviceInfo_3 = (TextView) findViewById(R.id.detail_overview_3);
        mDeviceInfo_4 = (TextView) findViewById(R.id.detail_overview_4);
        summary = (TextView) findViewById(R.id.summary);


        deviceName = intentThatStartedThisActivity.getStringExtra("deviceName");
        deviceInfo_1 = intentThatStartedThisActivity.getStringExtra("deviceInfo_1");
        deviceInfo_2 = intentThatStartedThisActivity.getStringExtra("deviceInfo_2");
        deviceInfo_3 = intentThatStartedThisActivity.getStringExtra("deviceInfo_3");
        deviceInfo_4 = intentThatStartedThisActivity.getStringExtra("deviceInfo_4");
        videoUrl = intentThatStartedThisActivity.getStringExtra("videoUrl");
        pdfUrl = intentThatStartedThisActivity.getStringExtra("pdfUrl");


        modelList.add(0, deviceInfo_1);
        modelList.add(1, deviceInfo_2);
        modelList.add(2, deviceInfo_3);
        modelList.add(3, deviceInfo_4);


        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("deviceImage")) {
                String image = intentThatStartedThisActivity.getStringExtra("deviceImage");
                Picasso.with(this).load(image.toString()).into(mDeviceImage);
            }
            if (intentThatStartedThisActivity.hasExtra("deviceName"))
                mDeviceName.setText(intentThatStartedThisActivity.getStringExtra("deviceName"));

            if (intentThatStartedThisActivity.hasExtra("deviceInfo_1"))
                mDeviceInfo_1.setText(intentThatStartedThisActivity.getStringExtra("deviceInfo_1"));

            if (intentThatStartedThisActivity.hasExtra("deviceInfo_2"))
                mDeviceInfo_2.setText(intentThatStartedThisActivity.getStringExtra("deviceInfo_2"));

            if (intentThatStartedThisActivity.hasExtra("deviceInfo_3"))
                mDeviceInfo_3.setText(intentThatStartedThisActivity.getStringExtra("deviceInfo_3"));

            if (intentThatStartedThisActivity.hasExtra("deviceInfo_4"))
                mDeviceInfo_4.setText(intentThatStartedThisActivity.getStringExtra("deviceInfo_4"));

            if (intentThatStartedThisActivity.hasExtra("deviceType"))
                mDeviceType.setText(intentThatStartedThisActivity.getStringExtra("deviceType"));
        }


        prepareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if (!device.getVideoURL().equals("")) {
                    Intent i = new Intent(mConte, VideoActivity.class);
                    i.putExtra("videoUrl", videoUrl);
                    startActivity(i);
            }
        });

        preparePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mConte, PdfActivity.class);
                intent.putExtra("pdfUrl", pdfUrl);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity_recipe_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // click on 'up' button in the action bar, handle it here
                onBackPressed();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        } else
            super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
