package com.example.android.networkdevicesdatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {



            @Override
            public void run() {

                ImageView welcomeScreen = (ImageView) findViewById(R.id.welcome_screen);


                Intent intent = new Intent(SplashActivity.this, MainActivity.class);  //MainActivity is my home activity.
                startActivity(intent );

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

