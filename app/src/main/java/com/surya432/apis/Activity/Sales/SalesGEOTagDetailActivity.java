package com.surya432.apis.Activity.Sales;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.surya432.apis.Activity.Sales.Model.CustomerModel;
import com.surya432.apis.R;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SalesGEOTagDetailActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback, View.OnClickListener {

    public static final String EXTRA_DATA = "EXTRA_DATA";
    private static final String TAG = SalesGEOTagDetailActivity.class.getSimpleName();

    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.koordinatMap)
    TextView koordinatMap;
    @BindView(R.id.salesGeoTagDetail)
    CoordinatorLayout salesGeoTagDetail;
    @BindView(R.id.alamatCustomer)
    TextView alamatCustomer;
    private GoogleMap sMap;
    private SupportMapFragment mapFragment;
    private LocationManager locationManager;
    private String lat = "";
    private String lng = "";
    private LatLng latLng;
    private CustomerModel customerModel;
    private int position;
    private Boolean isEdit = false;

    @OnClick(R.id.GetTag)
    void gettag() {
        getLocation();
        Log.d(TAG, "gettag: ");
    }

    @OnClick(R.id.idReset)
    void reset() {
        koordinatMap.setText("");
        Log.d(TAG, "reset: ");
        sMap.clear();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_geotag_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        customerModel = getIntent().getParcelableExtra(EXTRA_DATA);
        Log.d(TAG, "onCreate: "+customerModel);
        if (customerModel != null) {
            getSupportActionBar().setTitle("EDIT");
            position = getIntent().getIntExtra(EXTRA_DATA, 0);
            latLng = new LatLng(122.213123, 123.232123);
            isEdit = true;
            getLocation();
        } else {

            getSupportActionBar().setTitle("Tambah");
            getLocation();
            mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        btnSimpan.setOnClickListener(this);

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
        getLocation();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 1, this);
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
            Log.d(TAG, "onLocationChanged: Adress " + addresses.get(0).getAddressLine(0));
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            lat = Double.toString(location.getLatitude());
            lng = Double.toString(location.getLongitude());
            koordinatMap.setText(lat + "," + lng);
            alamatCustomer.setText(addresses.get(0).getAddressLine(0));
            Log.d(TAG, "onMapReady: " + lat + lng + "," + addresses.get(0).toString());
            SetTag();

        } catch (Exception e) {

        }
    }

    private void SetTag() {
        sMap.clear();
        sMap.addMarker(new MarkerOptions().position(latLng).title("Kamu Disini..."));
        sMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
        sMap.animateCamera(CameraUpdateFactory.zoomIn());
        sMap.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
        // Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng)      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(45)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        sMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        Snackbar.make(salesGeoTagDetail, "Lokasi Berhasil Di Update", Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSimpan:
                String LatLNG = latLng.toString();
                Log.d(TAG, "onClick: " + lat + ", " + lng);
                String Optioonns = "";
                if (isEdit) {
                    Optioonns = "Update";

                } else {
                    Optioonns = "SImpan";
                }
                Snackbar.make(salesGeoTagDetail, "data loc " + LatLNG+" "+ Optioonns, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
                break;

        }
    }
}
