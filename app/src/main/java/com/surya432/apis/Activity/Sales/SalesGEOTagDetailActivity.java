package com.surya432.apis.Activity.Sales;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.surya432.apis.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class SalesGEOTagDetailActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback, View.OnClickListener {

    private static final String TAG = SalesGEOTagDetailActivity.class.getSimpleName();
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    private GoogleMap sMap;
    private SupportMapFragment mapFragment;
    private LocationManager locationManager;
    private String lat = "";
    private String lng = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_geotag_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getLocation();
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        btnSimpan.setOnClickListener(SalesGEOTagActivity.this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        sMap = googleMap;

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, (android.location.LocationListener) this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged: " + location.getLatitude() + location.getLongitude());
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Log.d(TAG, "onLocationChanged: Adress " + addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            lat = Double.toString(location.getLatitude());
            lng = Double.toString(location.getLatitude());

            Log.d(TAG, "onMapReady: " + latLng);
            sMap.clear();
            sMap.addMarker(new MarkerOptions().position(latLng).title("Kamu Disini..."));
            sMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            sMap.animateCamera(CameraUpdateFactory.zoomTo(18));
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpan:
                String numberAsString = lat;
                String numberAsString2 = lng;
                Toast.makeText(SalesGEOTagDetailActivity.this, numberAsString + numberAsString2, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
