package com.example.android.networkdevicesdatabase;
/*
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class ReferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        TextView references = (TextView) findViewById(R.id.references);
        TextView first_reference = (TextView) findViewById(R.id.first_Reference);
        TextView second_reference = (TextView) findViewById(R.id.second_Reference);
        TextView data = (TextView) findViewById(R.id.data);
        TextView sheet_1 = (TextView) findViewById(R.id.sheet_1);
        TextView sheet_2 = (TextView) findViewById(R.id.sheet_2);


        data.setText(R.string.some_of);
        sheet_1.setText(R.string.device_manual);
        first_reference.setText(R.string.eng_name);
        sheet_2.setText(R.string.nmd);
        second_reference.setText(R.string.eng_name_2);
    }

    /*
    fbBtn.setOnClickListener ....
    public static String FACEBOOK_URL = "https://www.facebook.com/YourPageName";
    public static String FACEBOOK_PAGE_ID = "YourPageName";

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }*/

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // click on 'up' button in the action bar, handle it here
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
*/
