package com.surya432.skripsi.Activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ipaulpro.afilechooser.utils.FileUtils;
import com.surya432.skripsi.API.RestApi;
import com.surya432.skripsi.Adapter.AdapterFile;
import com.surya432.skripsi.Adapter.AdapterSpinnerStatus;
import com.surya432.skripsi.Helpers.APIHelper;
import com.surya432.skripsi.Helpers.NetworkManager;
import com.surya432.skripsi.Helpers.RetrofitClient;
import com.surya432.skripsi.Helpers.SessionManager;
import com.surya432.skripsi.Helpers.ToolUtil;
import com.surya432.skripsi.Model.ModelCreateTiket;
import com.surya432.skripsi.Model.ModelStatus;
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

public class CreateTiketActivity extends AppCompatActivity {
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_GALLERY_PHOTO = 2;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PICK_IMAGE_REQUEST = 12;
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
    @BindView(R.id.listView)
    ListView listView;
    private SessionManager sessionManager;
    private RestApi restApi;
    private AdapterSpinnerStatus adapterSpinnerStatus, adapterSpinnerDepartement, adapterSpinnerPrioritas;
    private Integer valSpStatus = 0, valSpDepartement = 0, valSpPrioritas = 0;
    private ArrayList<Uri> arrayList = new ArrayList<>();
    private ProgressDialog dialog;

    private void firstLoad() {
        dialog = ProgressDialog.show(CreateTiketActivity.this, "Loading", "Please wait...", true);
        sessionManager = new SessionManager(getApplicationContext());
        restApi = RetrofitClient.getClient().create(RestApi.class);
        setupSpinnerPrioritas();
        setupSpinnerDepartement();
        setupSpinnerStatus();
        dialog.dismiss();
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
                        int dataCount = response.body().getData().size() < 1 ? response.body().getData().size() : 0;
                        if (dataCount  < 1) {
                            List<ModelStatus.Data> item = response.body().getData();
                            adapterSpinnerDepartement = new AdapterSpinnerStatus(CreateTiketActivity.this, R.layout.modif_spinner_item, item);
                            spDepartement.setAdapter(adapterSpinnerDepartement);
                        }else{
                            ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Data Kosong");
                        }
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
                        int dataCount = response.body().getData().size() < 1 ? response.body().getData().size() : 0;
                        if (dataCount  < 1) {
                            List<ModelStatus.Data> item = response.body().getData();
                            adapterSpinnerPrioritas = new AdapterSpinnerStatus(CreateTiketActivity.this, R.layout.modif_spinner_item, item);
                            spPrioritas.setAdapter(adapterSpinnerPrioritas);
                        }else{
                            ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Data Kosong");
                        }
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

    private void setupSpinnerStatus() {
        if (!NetworkManager.isNetworkAvaliable(getApplicationContext())) {
            ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Koneksi Internet Tidak Ada");
        } else {
            Call<ModelStatus> call = restApi.doGetMasterStatus("services", sessionManager.getToken() + "");
            APIHelper.enqueueWithRetry(CreateTiketActivity.this, call, new Callback<ModelStatus>() {
                @Override
                public void onResponse(Call<ModelStatus> call, Response<ModelStatus> response) {
                    if (!response.isSuccessful()) {
                        ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Data Kosong");
                    } else {
                        int dataCount = response.body().getData().size() < 1 ? response.body().getData().size() : 0;
                        if (dataCount  < 1) {
                            List<ModelStatus.Data> item = response.body().getData();
                            adapterSpinnerStatus = new AdapterSpinnerStatus(CreateTiketActivity.this, R.layout.modif_spinner_item, item);
                            spStatus.setAdapter(adapterSpinnerStatus);
                        }else{
                            ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Data Kosong");
                        }

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
                valSpDepartement = adapterSpinnerDepartement.getSelectedItem2(position);
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
                valSpPrioritas = adapterSpinnerPrioritas.getSelectedItem2(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "onItemSelected: " + adapterSpinnerStatus.getSelectedItem2(position));
                valSpStatus = adapterSpinnerStatus.getSelectedItem2(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @OnClick({R.id.btnFile, R.id.btnCreateTiket})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnFile:
                showChooser();
                break;
            case R.id.btnCreateTiket:
                if (TextUtils.isEmpty(edtSubject.getText())) {
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Isi Subject");
                } else if (TextUtils.isEmpty(editTextBody.getText())) {
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Isi Pesan");
                } else if (valSpDepartement.equals(0)) {
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Pilih Departement");
                } else if (valSpPrioritas.equals(0)) {
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Pilih Prioritas");
                } else if (valSpStatus.equals(0)) {
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "Pilih Services");
                } else if (!valSpStatus.equals(0) && !valSpPrioritas.equals(0) && !valSpDepartement.equals(0)) {
                    dialog = ProgressDialog.show(CreateTiketActivity.this, "Loading", "Please wait...", true);
                    btnCreateTiket.setEnabled(false);
                    uploadImagesToServer();

                }
                break;
        }
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
                                String path = FileUtils.getPath(CreateTiketActivity.this, imageUri);
                                Log.d("Multiple File Selected", path);
                                arrayList.add(imageUri);
                                AdapterFile mAdapter = new AdapterFile(CreateTiketActivity.this, arrayList);
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
                            final String path = FileUtils.getPath(CreateTiketActivity.this, uri);
                            Log.d("Single File Selected", path);
                            arrayList.add(uri);
                            AdapterFile mAdapter = new AdapterFile(CreateTiketActivity.this, arrayList);
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

    private void uploadImagesToServer() {
        if (NetworkManager.isNetworkAvaliable(CreateTiketActivity.this)) {
            List<MultipartBody.Part> parts = new ArrayList<>();
            if (arrayList != null) {
                // create part for file (photo, video, ...)
                for (int i = 0; i < arrayList.size(); i++) {
                    parts.add(prepareFilePart("attachment[]", arrayList.get(i)));
                }
            }
            // create a map of data to pass along
            RequestBody subject = createPartFromString(edtSubject.getText().toString());
            RequestBody body = createPartFromString(editTextBody.getText().toString());
            RequestBody prioritas_id = createPartFromString(valSpPrioritas.toString());
            RequestBody user_id = createPartFromString(sessionManager.getId());
            RequestBody status_id = createPartFromString("1");
            RequestBody services_id = createPartFromString(valSpStatus.toString());
            RequestBody departement_id = createPartFromString(valSpDepartement.toString());
            RequestBody senders = createPartFromString(sessionManager.getName());
            HashMap<String, RequestBody> requestMap = new HashMap<>();
            requestMap.put("subject", subject);
            requestMap.put("body", body);
            requestMap.put("prioritas_id", prioritas_id);
            requestMap.put("user_id", user_id);
            requestMap.put("status_id", status_id);
            requestMap.put("services_id", services_id);
            requestMap.put("departement_id", departement_id);
            requestMap.put("senders", senders);
            // finally, execute the request
            Call<ModelCreateTiket> call = restApi.uploadMultipleFilesDynamic(requestMap, parts, sessionManager.getToken());
            call.enqueue(new Callback<ModelCreateTiket>() {
                @Override
                public void onResponse(@NonNull Call<ModelCreateTiket> call, @NonNull Response<ModelCreateTiket> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreateTiketActivity.this);
                        builder.setTitle("Response Server:");
                        builder.setMessage(response.body().getMsg())
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                    }
                                });
                        builder.setMessage(response.body().getMsg());
                        builder.setCancelable(false);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "" + response.body());
                    }
                    btnCreateTiket.setEnabled(true);
                }

                @Override
                public void onFailure(@NonNull Call<ModelCreateTiket> call, @NonNull Throwable t) {
                    dialog.dismiss();
                    ToolUtil.BuildAlertDialog(CreateTiketActivity.this, t.getMessage());
                    btnCreateTiket.setEnabled(true);
                }
            });
        } else {
            ToolUtil.BuildAlertDialog(CreateTiketActivity.this, "NO SELECTED");

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
