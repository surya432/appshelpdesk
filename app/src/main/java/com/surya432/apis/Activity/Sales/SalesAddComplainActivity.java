package com.surya432.apis.Activity.Sales;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.surya432.apis.Activity.Sales.Adapter.DDLComplainModel;
import com.surya432.apis.Activity.Sales.Model.ModelSpinner;
import com.surya432.apis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SalesAddComplainActivity extends AppCompatActivity {
    @BindView(R.id.namaCustomer)
    TextView namaCustomer;
    @BindView(R.id.dropdownDapertemen)
    Spinner dropdownDapertemen;
    @BindView(R.id.SalesbtnSimpanAddComplain)
    LinearLayout SalesbtnSimpanAddComplain;
    private DDLComplainModel ddlComplainModel;
    private Unbinder unbinder;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        unbinder =ButterKnife.bind(this);
        List<ModelSpinner> list = new ArrayList<ModelSpinner>();
        list.add(new ModelSpinner("1", "List1"));
        list.add(new ModelSpinner("2", "List4"));
        list.add(new ModelSpinner("3", "List3"));
        list.add(new ModelSpinner("4", "List2"));
        ddlComplainModel = new DDLComplainModel(getApplicationContext(), R.layout.modif_spinner_item, list);
        dropdownDapertemen.setAdapter(ddlComplainModel);
        SalesbtnSimpanAddComplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
