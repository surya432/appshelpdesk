package com.surya432.skripsi.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.ipaulpro.afilechooser.utils.FileUtils;
import com.surya432.skripsi.API.RestApi;
import com.surya432.skripsi.Adapter.AdapterFile;
import com.surya432.skripsi.Adapter.MessageListAdapter;
import com.surya432.skripsi.Helpers.APIHelper;
import com.surya432.skripsi.Helpers.NetworkManager;
import com.surya432.skripsi.Helpers.RetrofitClient;
import com.surya432.skripsi.Helpers.SessionManager;
import com.surya432.skripsi.Helpers.ToolUtil;
import com.surya432.skripsi.Model.ModelContentTiket;
import com.surya432.skripsi.Model.ModelCreateTiket;
import com.surya432.skripsi.Model.ModelListTiket;
import com.surya432.skripsi.Model.ModelTiket;
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

    static final int PICK_IMAGE_REQUEST = 12;
    private static final String TAG = DetailTiketActivity.class.getSimpleName();
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
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.attachment)
    ImageButton attachment;
    private ModelListTiket.DataBean m;
    private SessionManager sessionManager;
    private RestApi restApi;
    private String idTiket = "";
    private int dataCount = 0;
    private ArrayList<Uri> arrayList = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Membaca file menu dan menambahkan isinya ke action bar jika ada.
        getMenuInflater().inflate(R.menu.menu_detail_tiket, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tiket);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chating");
        if (getIntent().hasExtra("Data")) {
            m = getIntent().getParcelableExtra("Data");
            //ToolUtil.BuildAlertDialog(DetailTiketActivity.this, m.getId()+"");
            idTiket = String.valueOf(m.getId());
            firstLoad();
        } else if (getIntent().hasExtra("DATAFCM")) {
            idTiket = getIntent().getStringExtra("DATAFCM");
            Log.e(TAG, "onCreate: " + idTiket);
            firstLoad();
        } else {
            finish();
        }

    }

    private void firstLoad() {
        sessionManager = new SessionManager(getApplicationContext());
        restApi = RetrofitClient.getClient().create(RestApi.class);
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooser();
            }
        });
        setupListTiket();
        if (dataCount >= 1) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    //do something
                    handler.postDelayed(this, 5000);
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
            Call<ModelContentTiket> call = restApi.doGetContentTiket("tiketContent", String.valueOf(idTiket), sessionManager.getToken() + "");
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
                            listView.setVisibility(View.GONE);
                            listView.setAdapter(null);
                            arrayList.clear();
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
                MultipartBody.FORM, descriptionString);
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

    public void onComposeAction(MenuItem item) {

    }

    public void onClosedTiket(MenuItem item) {
        Call<ModelCreateTiket> call = restApi.doClosedTiket("closedTiket", "2", idTiket, sessionManager.getToken());
        APIHelper.enqueueWithRetry(DetailTiketActivity.this, call, new Callback<ModelCreateTiket>() {
            @Override
            public void onResponse(Call<ModelCreateTiket> call, Response<ModelCreateTiket> response) {
                if (!response.isSuccessful()) {
                    ToolUtil.BuildAlertDialog(DetailTiketActivity.this,"Terjadi Kesalahan.");
                }else{
                    ToolUtil.BuildAlertDialog(DetailTiketActivity.this,"Berhasil Di Simpan");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ModelCreateTiket> call, Throwable t) {

            }
        });
    }

    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent();
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, "Pilih File");
        try {
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data.getClipData() != null) {
                        int count = data.getClipData().getItemCount();
                        int currentItem = 0;
                        while (currentItem < count) {
                            Uri imageUri = data.getClipData().getItemAt(currentItem).getUri();
                            //do something with the image (save it to some directory or whatever you need to do with it here)
                            currentItem = currentItem + 1;
                            Log.d("Uri Selected", imageUri.toString());
                            try {
                                // Get the file path from the URI
                                String path = FileUtils.getPath(DetailTiketActivity.this, imageUri);
                                Log.d("Multiple File Selected", path);
                                arrayList.add(imageUri);

                                listView.setVisibility(View.VISIBLE);
                                AdapterFile mAdapter = new AdapterFile(DetailTiketActivity.this, arrayList);
                                listView.setAdapter(mAdapter);
                            } catch (Exception e) {
                                Log.e(TAG, "File select error", e);
                            }
                        }
                    } else if (data.getData() != null) {
                        //do something with the image (save it to some directory or whatever you need to do with it here)
                        final Uri uri = data.getData();
                        Log.i(TAG, "Uri = " + uri.toString());
                        try {
                            // Get the file path from the URI
                            final String path = FileUtils.getPath(DetailTiketActivity.this, uri);
                            Log.d("Single File Selected", path);
                            arrayList.add(uri);
                            listView.setVisibility(View.VISIBLE);
                            AdapterFile mAdapter = new AdapterFile(DetailTiketActivity.this, arrayList);
                            listView.setAdapter(mAdapter);
                        } catch (Exception e) {
                            Log.e(TAG, "File select error", e);
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
