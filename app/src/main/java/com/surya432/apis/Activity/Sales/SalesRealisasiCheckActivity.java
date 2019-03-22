package com.surya432.apis.Activity.Sales;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.surya432.apis.Activity.Sales.Model.CustomerModel;
import com.surya432.apis.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.surya432.apis.Activity.Sales.SalesGEOTagDetailActivity.EXTRA_DATA;

public class SalesRealisasiCheckActivity extends AppCompatActivity {
    private static final String TAG = SalesRealisasiCheckActivity.class.getSimpleName();
    @BindView(R.id.BtncheckIn)
    Button checkIn;
    @BindView(R.id.BtnBukaMap)
    Button BukaMap;
    @BindView(R.id.rowRealisasi)
    LinearLayout rowRealisasi;
    @BindView(R.id.valNoTlpn)
    TextView valNoTlpn;
    @BindView(R.id.valCheckOut)
    TextView valCheckOut;
    @BindView(R.id.valCheckin)
    TextView valCheckIn;
    @BindView(R.id.lapaoranChekout)
    TextInputLayout lapaoranChekout;
    @BindView(R.id.BtnListComplain)
    Button ListComplain;
    @BindView(R.id.BtnSalesUpdateJadwal)
    Button SalesUpdateJadwal;
    @BindView(R.id.BtnSalesAddJadwal)
    Button SalesAddJadwal;
    @BindView(R.id.BtnSalesGeoTagUpdate)
    Button BtnSalesGeoTagUpdate;
    private Boolean SalesCheckIn = false;

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
                String url = valNoTlpn.getText().toString().substring(1, valNoTlpn.length());
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // Send phone number to intent as data
                intent.setData(Uri.parse("tel:+62" + url));
                // Start the dialer app activity with number
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Detail Customer");
        String Data = getIntent().getStringExtra("Data");
        switch (Data) {
            case "Realisasi":
                SalesCheckIn = false;
                rowRealisasi.setVisibility(View.VISIBLE);
                break;
            case "Planning":
                SalesUpdateJadwal.setVisibility(View.VISIBLE);
                checkIn.setVisibility(View.GONE);
                break;
            case "Customer":
                SalesAddJadwal.setVisibility(View.VISIBLE);
                checkIn.setVisibility(View.GONE);
                break;
            case "GeoTag":
                BtnSalesGeoTagUpdate.setVisibility(View.VISIBLE);
                checkIn.setVisibility(View.GONE);
                break;
        }
        SalesCheckIn = true;
        lapaoranChekout.setVisibility(View.GONE);
        checkIn.setText("Check In");
        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat df = new SimpleDateFormat("HH:mm");
                String date = df.format(Calendar.getInstance().getTime());
                if (SalesCheckIn) {
                    valCheckIn.setText(date);
                    Toast.makeText(getApplicationContext(), "Berhasil Check In " + date, Toast.LENGTH_SHORT).show();
                    checkIn.setText("Check Out");
                    SalesCheckIn = false;
                    lapaoranChekout.setVisibility(View.VISIBLE);
                } else {
                    lapaoranChekout.setVisibility(View.GONE);
                    SalesCheckIn = true;
                    valCheckOut.setText(date);
                    Toast.makeText(getApplicationContext(), "Berhasil Check Out " + date, Toast.LENGTH_SHORT).show();
                    checkIn.setText("Check In");
                }
            }
        });

        BukaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:-7.3310465,112.779884?z=18");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
        valNoTlpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = valNoTlpn.getText().toString().substring(1, valNoTlpn.length());
                Intent intent = new Intent(Intent.ACTION_DIAL);
                // Send phone number to intent as data
                intent.setData(Uri.parse("tel:+62" + url));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        ListComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SalesComplainTrackingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        SalesAddJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(), SalesJadwalFormActivity.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              intent.putExtra("EXTRA_DATA", false);
              startActivity(intent);
            }
        });

        SalesUpdateJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SalesJadwalFormActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("EXTRA_DATA", true);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
