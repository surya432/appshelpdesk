package com.surya432.skripsi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.surya432.skripsi.API.RestApi;
import com.surya432.skripsi.Adapter.AdapterArtikel;
import com.surya432.skripsi.Helpers.APIHelper;
import com.surya432.skripsi.Helpers.NetworkManager;
import com.surya432.skripsi.Helpers.RetrofitClient;
import com.surya432.skripsi.Helpers.SessionManager;
import com.surya432.skripsi.Helpers.ToolUtil;
import com.surya432.skripsi.Model.ModelArtikel;
import com.surya432.skripsi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListArticleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listItem)
    RecyclerView listItem;
    private RestApi restApi;
    private SessionManager sessionManager;
    private Intent intent;
    private int dataCount = 0;
    private Boolean runable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_article);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firstLoad();
    }

    private void firstLoad() {
        sessionManager = new SessionManager(getApplicationContext());
        restApi = RetrofitClient.getClient().create(RestApi.class);
        setupListTiket();


    }

    private void setupListTiket() {
        if (!NetworkManager.isNetworkAvaliable(getApplicationContext())) {
            ToolUtil.BuildAlertDialog(ListArticleActivity.this, "Koneksi Internet Tidak Ada");
        } else {
            Call<ModelArtikel> call = restApi.doGetListArtikel("artikel", sessionManager.getToken() + "");
            APIHelper.enqueueWithRetry(getApplicationContext(), call, new Callback<ModelArtikel>() {
                @Override
                public void onResponse(Call<ModelArtikel> call, Response<ModelArtikel> response) {

                    try {
                        if (!response.isSuccessful()) {
                            listItem.setVisibility(View.GONE);
                            ToolUtil.BuildAlertDialog(ListArticleActivity.this, "Data Kosong");
                        } else {
                            dataCount = response.body().getData().size() > 0 ? response.body().getData().size() : 0;
                            Log.e("datacount", "onResponse: datacount" + dataCount);
                            if (dataCount > 0) {
                                runable = true;
                                List<ModelArtikel.DataBean> dataBeans = response.body().getData();
                                LinearLayoutManager llm = new LinearLayoutManager(ListArticleActivity.this);
                                llm.setOrientation(LinearLayoutManager.VERTICAL);
                                listItem.setLayoutManager(llm);
                                listItem.setHasFixedSize(true);
                                listItem.setAdapter(null);
                                listItem.setVisibility(View.VISIBLE);
                                AdapterArtikel adapterListTiket = new AdapterArtikel(getApplicationContext(), dataBeans);
                                listItem.setAdapter(adapterListTiket);
                            } else {
                                ToolUtil.BuildAlertDialog(ListArticleActivity.this, "Data Kosong");
                            }

                        }
                    } catch (Exception e) {
                        ToolUtil.BuildAlertDialog(ListArticleActivity.this, "Error " + e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<ModelArtikel> call, Throwable t) {
                    listItem.setVisibility(View.GONE);
                    ToolUtil.BuildAlertDialog(getApplicationContext(), t.getMessage());
                }
            });

        }
    }
}
