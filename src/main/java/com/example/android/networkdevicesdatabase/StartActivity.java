package com.example.android.networkdevicesdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StartActivity extends AppCompatActivity {


    @BindView(R.id.btnLogin)
    Button  btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    EditText etEmail,etPassword;
    String stringEmail,stringPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Unbinder unbinder = ButterKnife.bind(this);

        //btnRegister = (Button) findViewById(R.id.btnRegister);
        //btnLogin = (Button) findViewById(R.id.btnLogin);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringEmail = etEmail.getText().toString();
                stringPassword = etPassword.getText().toString();
                String task = "login";
                BackgroundTask backgroundTask = new BackgroundTask(StartActivity.this);

                etEmail.setText("");
                etPassword.setText("");

                //execute the task
                //passes the paras to the backgroundTask (param[0],param[1],param[2])
                backgroundTask.execute(task,stringEmail,stringPassword);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
}
