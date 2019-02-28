package com.surya432.apis.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.surya432.apis.MainActivity;
import com.surya432.apis.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class LoginActivity extends AppCompatActivity {
    private Intent intent;

    @Optional
    @OnClick(R.id.btn_login)
    public void btnLogin() {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
