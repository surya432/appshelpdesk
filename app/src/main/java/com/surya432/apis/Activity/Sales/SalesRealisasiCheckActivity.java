package com.surya432.apis.Activity.Sales;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.surya432.apis.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesRealisasiCheckActivity extends AppCompatActivity {
    private static final String TAG = SalesRealisasiCheckActivity.class.getSimpleName();
    @BindView(R.id.checkIn)
    Button checkIn;
    @BindView(R.id.BukaMap)
    Button BukaMap;

    @BindView(R.id.valNoTlpn)
    TextView valNoTlpn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_realisasi_check);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Detail Customer");
        String Data = getIntent().getStringExtra("Data");
        switch (Data) {
            case "Realisasi":

                break;
            case "Planning":
                checkIn.setVisibility(View.GONE);
                break;
            case "Customer":
                checkIn.setVisibility(View.GONE);
                break;

        }
        checkIn.setText("Check In");
        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Berhasil Check In", Toast.LENGTH_SHORT).show();
                checkIn.setText("Check Out");
            }
        });

        BukaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:-7.3310465,112.779884?z=18");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
        valNoTlpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = valNoTlpn.getText().toString().substring(1,valNoTlpn.length());
                Intent intent = new Intent(Intent.ACTION_DIAL);
                // Send phone number to intent as data
                intent.setData(Uri.parse("tel:+62" + url));
                // Start the dialer app activity with number
                startActivity(intent);
                /*Log.d(TAG, "onClick: "+url);
                try {
                    PackageManager pm = getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("https://api.whatsapp.com/send?phone=+62" + url));
                    startActivity(i);
                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
