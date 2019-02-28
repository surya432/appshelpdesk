package com.surya432.apis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Optional;

public class MainActivity extends AppCompatActivity {
    private String versionName = BuildConfig.VERSION_NAME;
    @BindView(R.id.versionApk)
    TextView versionApk;

    private void loaded() {
        versionApk.setText("V."+versionName);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loaded();
    }
}
