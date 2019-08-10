package com.surya432.skripsi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.surya432.skripsi.API.RestApi;
import com.surya432.skripsi.Adapter.AdapterListTiket;
import com.surya432.skripsi.Helpers.APIHelper;
import com.surya432.skripsi.Helpers.NetworkManager;
import com.surya432.skripsi.Helpers.RetrofitClient;
import com.surya432.skripsi.Helpers.SessionManager;
import com.surya432.skripsi.Helpers.ToolUtil;
import com.surya432.skripsi.Model.ModelListTiket;
import com.surya432.skripsi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTiketActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listItem)
    RecyclerView listItem;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    Handler handler = new Handler();
    private RestApi restApi;
    private SessionManager sessionManager;
    private Intent intent;
    private int dataCount = 0;
    private Boolean runable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tiket);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ListTiketActivity.this, CreateTiketActivity.class);
                startActivity(intent);
            }
        });
        firstLoad();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        firstLoad();
    }

    private void firstLoad() {
        sessionManager = new SessionManager(getApplicationContext());
        restApi = RetrofitClient.getClient().create(RestApi.class);
        setupListTiket();
        runad();

    }

    private void setupListTiket() {
        if (!NetworkManager.isNetworkAvaliable(getApplicationContext())) {
            ToolUtil.BuildAlertDialog(ListTiketActivity.this, "Koneksi Internet Tidak Ada");
        } else {
            Call<ModelListTiket> call = restApi.doGetListTiket("tiket", sessionManager.getToken() + "");
            APIHelper.enqueueWithRetry(getApplicationContext(), call, new Callback<ModelListTiket>() {
                @Override
                public void onResponse(Call<ModelListTiket> call, Response<ModelListTiket> response) {
                    try {

                        if (!response.isSuccessful()) {
                            listItem.setVisibility(View.GONE);
                            ToolUtil.BuildAlertDialog(ListTiketActivity.this, "Data Kosong");
                        } else {
                            dataCount = response.body().getData().size() > 0 ? response.body().getData().size() : 0;
                            Log.e("datacount", "onResponse: datacount" + dataCount);
                            if (dataCount > 0) {
                                runable = true;
                                List<ModelListTiket.DataBean> dataBeans = response.body().getData();
                                LinearLayoutManager llm = new LinearLayoutManager(ListTiketActivity.this);
                                llm.setOrientation(LinearLayoutManager.VERTICAL);
                                listItem.setLayoutManager(llm);
                                listItem.setHasFixedSize(true);
                                listItem.setAdapter(null);
                                listItem.setVisibility(View.VISIBLE);
                                AdapterListTiket adapterListTiket = new AdapterListTiket(getApplicationContext(), dataBeans);
                                listItem.setAdapter(adapterListTiket);
                            } else {
                                ToolUtil.BuildAlertDialog(ListTiketActivity.this, "Data Kosong");
                            }

                        }
                    } catch (Exception e) {
                        ToolUtil.BuildAlertDialog(ListTiketActivity.this, "Error Koneksi "+e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ModelListTiket> call, Throwable t) {
                    listItem.setVisibility(View.GONE);
                    handler.removeCallbacksAndMessages(null);
                    ToolUtil.BuildAlertDialog(ListTiketActivity.this, t.getMessage());
                }
            });

        }
    }

    private void runad() {
        if (dataCount >= 1) {
            Log.e("runad", "onResponse: " + "enavle");

            handler.postDelayed(new Runnable() {
                public void run() {
                    //do something
                    handler.postDelayed(this, 50000);
                    setupListTiket();

                }
            }, 10000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }


    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacksAndMessages(null);

    }
}
