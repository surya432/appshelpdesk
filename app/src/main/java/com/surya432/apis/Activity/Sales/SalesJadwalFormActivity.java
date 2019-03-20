package com.surya432.apis.Activity.Sales;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.surya432.apis.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesJadwalFormActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = SalesJadwalFormActivity.class.getSimpleName();
    @BindView(R.id.SalesUpdateTanggal)
    TextView SalesUpdateTanggal;
    @BindView(R.id.btnSimpan)
    LinearLayout btnSimpan;
    @BindView(R.id.SalesUpdateJamMulai)
    TextView SalesUpdateJamMulai;
    @BindView(R.id.SalesUpdateJamSelesai)
    TextView SalesUpdateJamSelesai;
    private Boolean isEdit;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_jadwal_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        isEdit = getIntent().getBooleanExtra("EXTRA_DATA", false);
        if (isEdit) {
            getSupportActionBar().setTitle("Edit Kegiatan");
        } else {
            String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
            String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            SalesUpdateJamMulai.setText(currentTime);
            SalesUpdateJamSelesai.setText(currentTime);
            SalesUpdateTanggal.setText(currentDate);
            getSupportActionBar().setTitle("Tambah Kegiatan");
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        SalesUpdateTanggal.setOnClickListener(this);
        SalesUpdateJamMulai.setOnClickListener(this);
        SalesUpdateJamSelesai.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {

        Calendar calendar = Calendar.getInstance();
        switch (v.getId()) {
            case R.id.SalesUpdateTanggal:
                Calendar newCalendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String formatted = dateFormat.format(newDate.getTime());
                        SalesUpdateTanggal.setText(formatted);
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
            case R.id.SalesUpdateJamMulai:
                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SalesUpdateJamMulai.setText(hourOfDay + ":" + minute);
                    }
                },
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                        DateFormat.is24HourFormat(this));
                timePickerDialog.show();
                break;
            case R.id.SalesUpdateJamSelesai:
                timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        SalesUpdateJamSelesai.setText(hourOfDay + ":" + minute);
                    }
                },
                        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
                        DateFormat.is24HourFormat(this));
                timePickerDialog.show();
                break;
            case R.id.btnSimpan:
                break;
        }
    }
}
