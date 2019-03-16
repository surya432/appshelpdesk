package com.surya432.apis;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.surya432.apis.Activity.Sales.Adapter.DDLComplainModel;
import com.surya432.apis.Activity.Sales.Model.ModelSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesAddComplainActivity extends AppCompatActivity {
    @BindView(R.id.namaCustomer)
    TextView namaCustomer;
    private DDLComplainModel ddlComplainModel;
    @BindView(R.id.dropdownDapertemen)
    Spinner dropdownDapertemen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_add_complain);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tambah Complain");
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ButterKnife.bind(this);
        List<ModelSpinner> list = new ArrayList<ModelSpinner>();
        list.add(new ModelSpinner("1", "List1"));
        list.add(new ModelSpinner("2", "List4"));
        list.add(new ModelSpinner("3", "List3"));
        list.add(new ModelSpinner("4", "List2"));
        ddlComplainModel = new DDLComplainModel(getApplicationContext(), R.layout.modif_spinner_item, list);
        dropdownDapertemen.setAdapter(ddlComplainModel);
    }

}
