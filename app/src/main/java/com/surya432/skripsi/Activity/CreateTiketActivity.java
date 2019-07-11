package com.surya432.skripsi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.surya432.skripsi.API.RestApi;
import com.surya432.skripsi.Adapter.AdapterSpinnerStatus;
import com.surya432.skripsi.Helpers.APIHelper;
import com.surya432.skripsi.Helpers.NetworkManager;
import com.surya432.skripsi.Helpers.RetrofitClient;
import com.surya432.skripsi.Helpers.SessionManager;
import com.surya432.skripsi.Helpers.ToolUtil;
import com.surya432.skripsi.Model.ModelStatus;
import com.surya432.skripsi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTiketActivity extends AppCompatActivity {
    private static final String TAG = CreateTiketActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edtSubject)
    EditText edtSubject;
    @BindView(R.id.spPrioritas)
    Spinner spPrioritas;
    @BindView(R.id.spDepartement)
    Spinner spDepartement;
    @BindView(R.id.spStatus)
    Spinner spStatus;
    @BindView(R.id.editTextBody)
    EditText editTextBody;
    @BindView(R.id.btnFile)
    Button btnFile;
    @BindView(R.id.tvFileName)
    TextView tvFileName;
    @BindView(R.id.btnCreateTiket)
    LinearLayout btnCreateTiket;
    private SessionManager sessionManager;
    private RestApi restApi;
    private Intent intent;
    private String valSpStatus, valSpDepartement, valSpPrioritas = "";
    private AdapterSpinnerStatus adapterSpinnerStatus, adapterSpinnerDepartement, adapterSpinnerPrioritas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tiket);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firstLoad();
        spDepartement.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.e(TAG, "onItemSelected: " + adapterSpinnerDepartement.getSelectedItem2(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spPrioritas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemSelected: " + adapterSpinnerPrioritas.getSelectedItem2(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemSelected: " + adapterSpinnerStatus.getSelectedItem2(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void firstLoad() {
        sessionManager = new SessionManager(getApplicationContext());
        restApi = RetrofitClient.getClient().create(RestApi.class);
        setupSpinnerPrioritas();
        setupSpinnerDepartement();
        setupSpinnerStatus();
    }

    private void setupSpinnerStatus() {
        if (!NetworkManager.isNetworkAvaliable(getApplicationContext())) {
            ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Koneksi Internet Tidak Ada");
        } else {
            Call<ModelStatus> call = restApi.doGetMasterStatus("status", sessionManager.getToken() + "");
            APIHelper.enqueueWithRetry(CreateTiketActivity.this, call, new Callback<ModelStatus>() {
                @Override
                public void onResponse(Call<ModelStatus> call, Response<ModelStatus> response) {
                    if (!response.isSuccessful()) {
                        ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Data Kosong");
                    } else {
                        List<ModelStatus.Data> item = response.body().getData();
                        adapterSpinnerStatus = new AdapterSpinnerStatus(CreateTiketActivity.this, R.layout.modif_spinner_item, item);
                        spStatus.setAdapter(adapterSpinnerStatus);

                    }
                }

                @Override
                public void onFailure(Call<ModelStatus> call, Throwable t) {
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, t.getMessage() + "");
                }
            });
        }
    }

    private void setupSpinnerDepartement() {
        if (!NetworkManager.isNetworkAvaliable(getApplicationContext())) {
            ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Koneksi Internet Tidak Ada");
        } else {
            Call<ModelStatus> call = restApi.doGetMasterStatus("departement", sessionManager.getToken() + "");
            APIHelper.enqueueWithRetry(CreateTiketActivity.this, call, new Callback<ModelStatus>() {
                @Override
                public void onResponse(Call<ModelStatus> call, Response<ModelStatus> response) {
                    if (!response.isSuccessful()) {
                        ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Data Kosong");
                    } else {
                        List<ModelStatus.Data> item = response.body().getData();
                        adapterSpinnerDepartement = new AdapterSpinnerStatus(CreateTiketActivity.this, R.layout.modif_spinner_item, item);
                        spDepartement.setAdapter(adapterSpinnerDepartement);

                    }
                }

                @Override
                public void onFailure(Call<ModelStatus> call, Throwable t) {
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, t.getMessage() + "");
                }
            });
        }
    }

    private void setupSpinnerPrioritas() {
        if (!NetworkManager.isNetworkAvaliable(getApplicationContext())) {
            ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Koneksi Internet Tidak Ada");
        } else {
            Call<ModelStatus> call = restApi.doGetMasterStatus("prioritas", sessionManager.getToken() + "");
            APIHelper.enqueueWithRetry(CreateTiketActivity.this, call, new Callback<ModelStatus>() {
                @Override
                public void onResponse(Call<ModelStatus> call, Response<ModelStatus> response) {
                    if (!response.isSuccessful()) {
                        ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Data Kosong");
                    } else {
                        List<ModelStatus.Data> item = response.body().getData();
                        adapterSpinnerPrioritas = new AdapterSpinnerStatus(CreateTiketActivity.this, R.layout.modif_spinner_item, item);
                        spPrioritas.setAdapter(adapterSpinnerPrioritas);
                    }
                }

                @Override
                public void onFailure(Call<ModelStatus> call, Throwable t) {
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, t.getMessage() + "");
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.btnFile, R.id.btnCreateTiket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnFile:
                break;
            case R.id.btnCreateTiket:
                break;
        }
    }
}
