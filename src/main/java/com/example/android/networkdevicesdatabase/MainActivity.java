package com.example.android.networkdevicesdatabase;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.networkdevicesdatabase.Fragments.CiscoFragment;
import com.example.android.networkdevicesdatabase.Fragments.HuaweiFragment;
import com.example.android.networkdevicesdatabase.Fragments.JuniperFragment;
import com.example.android.networkdevicesdatabase.Fragments.TopologyFragment;

public class MainActivity extends AppCompatActivity {


    private final static String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                            case R.id.cisco:
                                if (!CiscoFragment.newInstance().isInLayout())
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.frame_layout, new CiscoFragment())
                                            .addToBackStack(null)
                                            .commit();

                                //selectedFragment = CiscoFragment.newInstance();
                                break;
                            case R.id.juniper:
                                if (!JuniperFragment.newInstance().isInLayout())
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.frame_layout, new JuniperFragment())
                                            .addToBackStack(null)
                                            .commit();

                                //selectedFragment = JuniperFragment.newInstance();
                                break;
                            case R.id.huawei:
                                if (!HuaweiFragment.newInstance().isInLayout())
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.frame_layout, new HuaweiFragment())
                                            .addToBackStack(null)
                                            .commit();

                                //selectedFragment = HuaweiFragment.newInstance();
                                break;
                            case R.id.topology:
                                if (!TopologyFragment.newInstance().isInLayout())
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.frame_layout, new TopologyFragment())
                                            .addToBackStack(null)
                                            .commit();

                                //selectedFragment = TopologyFragment.newInstance();
                                break;

                            default:

                        }

                        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.addToBackStack("tag");
                        transaction.commit();*/

                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, CiscoFragment.newInstance());
        transaction.commit();


        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(1).setChecked(true);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            final Dialog dialog= new Dialog(this);
            View view = LayoutInflater.from(this).inflate(R.layout.activity_about,null);
            dialog.setContentView(view);
            dialog.setTitle("About Us");
            dialog.show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
