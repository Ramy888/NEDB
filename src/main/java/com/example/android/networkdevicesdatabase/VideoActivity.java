package com.example.android.networkdevicesdatabase;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.networkdevicesdatabase.Data.Device;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.no_video_text_view)
    TextView noVideoTxt;
    @BindView(R.id.media_player)
    PlayerView mPlayerView;
    @BindView(R.id.no_internet)
    TextView noInternet;
    private SimpleExoPlayer mExoPlayer;
    public static String video;
    public Uri mVideoUri;

    private Context mContext = this;
    private Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ButterKnife.bind(this);

        Intent intentThatStartedThisActivity = getIntent();
        video = device.getVideoURL();
        mVideoUri = Uri.parse(video);

       /* if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("videoUrl")) {
                video = intentThatStartedThisActivity.getStringExtra("videoUrl");
                mVideoUri = Uri.parse(video);
            }

        } */


        if (savedInstanceState == null) {
            ConnectivityManager connMgr = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (!video.equals("") && (networkInfo != null && networkInfo.isConnected())) {
                noVideoTxt.setVisibility(View.GONE);
                noInternet.setVisibility(View.GONE);
                initializePlayer(mVideoUri);
            } else if (video.equals("") && (networkInfo != null && networkInfo.isConnected())) {
                mPlayerView.setVisibility(View.GONE);
                noVideoTxt.setVisibility(View.VISIBLE);
                noInternet.setVisibility(View.GONE);
                noVideoTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            } else {
                mPlayerView.setVisibility(View.GONE);
                noVideoTxt.setVisibility(View.GONE);
                noInternet.setVisibility(View.VISIBLE);
                noInternet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            }
        }

        if (savedInstanceState != null) {
            device = (Device) savedInstanceState.getSerializable("ser");
            ConnectivityManager connMgr = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (!video.equals("") && (networkInfo != null && networkInfo.isConnected())) {
                noVideoTxt.setVisibility(View.GONE);
                initializePlayer(mVideoUri);
            } else if (video.equals("")) {
                mPlayerView.setVisibility(View.GONE);
                noVideoTxt.setVisibility(View.VISIBLE);
                noInternet.setVisibility(View.GONE);
                noVideoTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onBackPressed();
                    }
                });
            } else {
                mPlayerView.setVisibility(View.GONE);
                noVideoTxt.setVisibility(View.GONE);
                noInternet.setVisibility(View.VISIBLE);
            }
        }
    }


    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);

            String userAgent = Util.getUserAgent(getApplicationContext(), "Preparing Hardware");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    mContext, userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (!mVideoUri.equals("") && mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("ser", device);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onStop() {
        super.onStop();
        if (mVideoUri != null)
            releasePlayer();
    }

    public void setDevice(Device device) {
        this.device = device;
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
        super.onBackPressed();
    }
    /*
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
        super.onBackPressed();
    }*/
}
