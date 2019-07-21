package com.surya432.skripsi.Activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ipaulpro.afilechooser.utils.FileUtils;
import com.surya432.skripsi.API.RestApi;
import com.surya432.skripsi.Adapter.MessageListAdapter;
import com.surya432.skripsi.Helpers.APIHelper;
import com.surya432.skripsi.Helpers.NetworkManager;
import com.surya432.skripsi.Helpers.RetrofitClient;
import com.surya432.skripsi.Helpers.SessionManager;
import com.surya432.skripsi.Helpers.ToolUtil;
import com.surya432.skripsi.Model.ModelContentTiket;
import com.surya432.skripsi.Model.ModelCreateTiket;
import com.surya432.skripsi.Model.ModelListTiket;
import com.surya432.skripsi.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTiketActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.reyclerview_message_list)
    RecyclerView reyclerviewMessageList;
    @BindView(R.id.hr1)
    View hr1;
    @BindView(R.id.edittext_chatbox)
    EditText edittextChatbox;
    @BindView(R.id.button_chatbox_send)
    Button buttonChatboxSend;
    @BindView(R.id.layout_chatbox)
    LinearLayout layoutChatbox;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    Handler handler = new Handler();
    int delay = 5000; //milliseconds
    private ModelListTiket.DataBean m;
    private SessionManager sessionManager;
    private RestApi restApi;
    private int dataCount = 0;
    private ArrayList<Uri> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tiket);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("Data")) {
            m = getIntent().getParcelableExtra("Data");
            //ToolUtil.BuildAlertDialog(DetailTiketActivity.this, m.getId()+"");
            getSupportActionBar().setTitle(m.getSubject().toString());
            firstLoad();
        } else {
            finish();
        }

    }

    private void firstLoad() {
        sessionManager = new SessionManager(getApplicationContext());
        restApi = RetrofitClient.getClient().create(RestApi.class);
        setupListTiket();
        if (dataCount >= 1) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    //do something
                    handler.postDelayed(this, 3000);
                    setupListTiket();
                }
            }, 5000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    private void setupListTiket() {
        if (!NetworkManager.isNetworkAvaliable(getApplicationContext())) {
            ToolUtil.BuildAlertDialog(DetailTiketActivity.this, "Koneksi Internet Tidak Ada");
        } else {
            Call<ModelContentTiket> call = restApi.doGetContentTiket("tiketContent", String.valueOf(m.getId()), sessionManager.getToken() + "");
            APIHelper.enqueueWithRetry(getApplicationContext(), call, new Callback<ModelContentTiket>() {
                @Override
                public void onResponse(Call<ModelContentTiket> call, Response<ModelContentTiket> response) {
                    progressbar.setVisibility(View.GONE);
                    if (!response.isSuccessful()) {
                        reyclerviewMessageList.setVisibility(View.GONE);
                        ToolUtil.BuildAlertDialog(DetailTiketActivity.this, "Belum Ada Chat");
                    } else {
                        int dataCount2 = response.body().getData().size() < 1 ? response.body().getData().size() : 1;
                        Log.e("MSG", "onResponse: " + dataCount2 + dataCount);
                        if (dataCount2 > dataCount) {
                            LinearLayoutManager llm = new LinearLayoutManager(DetailTiketActivity.this);
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            llm.setStackFromEnd(true);
                            reyclerviewMessageList.setLayoutManager(llm);
                            reyclerviewMessageList.setHasFixedSize(true);
                            reyclerviewMessageList.setAdapter(null);
                            reyclerviewMessageList.setVisibility(View.VISIBLE);
                            List<ModelContentTiket.DataBean> dataBeans = response.body().getData();
                            MessageListAdapter adapterListTiket = new MessageListAdapter(getApplicationContext(), dataBeans);
                            reyclerviewMessageList.setAdapter(adapterListTiket);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ModelContentTiket> call, Throwable t) {
                    reyclerviewMessageList.setVisibility(View.GONE);
                    //ToolUtil.BuildAlertDialog(getApplicationContext(), t.getMessage());
                }
            });
        }
    }

    @OnClick(R.id.button_chatbox_send)
    public void onViewClicked() {
        dataCount = 0;
        uploadImagesToServer();
        edittextChatbox.setText("");

    }

    private void uploadImagesToServer() {
        if (NetworkManager.isNetworkAvaliable(DetailTiketActivity.this)) {
            List<MultipartBody.Part> parts = new ArrayList<>();
            if (arrayList != null) {
                // create part for file (photo, video, ...)
                for (int i = 0; i < arrayList.size(); i++) {
                    parts.add(prepareFilePart("attachment[]", arrayList.get(i)));
                }
            }
            String valRepply = "1";
            // create a map of data to pass along
            if (sessionManager.getRole().equals("User")) {
                valRepply = "0";
            }
            RequestBody repply = createPartFromString(valRepply);
            RequestBody body = createPartFromString(edittextChatbox.getText().toString());
            RequestBody status_id = createPartFromString(valRepply);
            RequestBody tiket_id = createPartFromString(String.valueOf(m.getId()));
            RequestBody senders = createPartFromString(sessionManager.getName());
            HashMap<String, RequestBody> requestMap = new HashMap<>();
            requestMap.put("body", body);
            requestMap.put("senders", senders);
            requestMap.put("tiket_id", tiket_id);
            requestMap.put("status_id", status_id);
            requestMap.put("repply", repply);
            Call<ModelContentTiket> call = restApi.doPostReplyTiket(requestMap, parts, sessionManager.getToken());
            call.enqueue(new Callback<ModelContentTiket>() {
                @Override
                public void onResponse(@NonNull Call<ModelContentTiket> call, @NonNull Response<ModelContentTiket> response) {
                    if (!response.isSuccessful()) {
                        reyclerviewMessageList.setVisibility(View.GONE);
                        ToolUtil.BuildAlertDialog(DetailTiketActivity.this, "Belum Ada Chat");
                    } else {
                        dataCount = response.body().getData().size() >= 1 ? response.body().getData().size() : 0;
                        if (dataCount > 0) {
                            LinearLayoutManager llm = new LinearLayoutManager(DetailTiketActivity.this);
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            llm.setStackFromEnd(true);
                            reyclerviewMessageList.setLayoutManager(llm);
                            reyclerviewMessageList.setHasFixedSize(true);
                            reyclerviewMessageList.setAdapter(null);
                            reyclerviewMessageList.setVisibility(View.VISIBLE);
                            List<ModelContentTiket.DataBean> dataBeans = response.body().getData();
                            MessageListAdapter adapterListTiket = new MessageListAdapter(getApplicationContext(), dataBeans);
                            reyclerviewMessageList.setAdapter(adapterListTiket);
                        }
                    }
                    buttonChatboxSend.setEnabled(true);
                }

                @Override
                public void onFailure(@NonNull Call<ModelContentTiket> call, @NonNull Throwable t) {
                   // ToolUtil.BuildAlertDialog(DetailTiketActivity.this, t.getMessage());
                    buttonChatboxSend.setEnabled(true);
                }
            });
        } else {
            ToolUtil.BuildAlertDialog(DetailTiketActivity.this, "NO SELECTED");
        }
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(Objects.requireNonNull(getContentResolver().getType(fileUri))),
                        file
                );
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
}
