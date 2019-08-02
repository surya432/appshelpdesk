package com.surya432.skripsi.API;

import com.surya432.skripsi.Model.ModelArtikel;
import com.surya432.skripsi.Model.ModelContentTiket;
import com.surya432.skripsi.Model.ModelCreateTiket;
import com.surya432.skripsi.Model.ModelListTiket;
import com.surya432.skripsi.Model.ModelLogin;
import com.surya432.skripsi.Model.ModelStatus;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface RestApi {
    @FormUrlEncoded
    @POST("login")
    Call<ModelLogin> doLogin(@Field("email") String Username, @Field("password") String Password);

    @FormUrlEncoded
    @POST("master")
    Call<ModelListTiket> doGetListTiket(@Field("TableName") String TableName, @Header("Authorization") String authHeader);
    @FormUrlEncoded
    @POST("master")
    Call<ModelArtikel> doGetListArtikel(@Field("TableName") String TableName, @Header("Authorization") String authHeader);
    @FormUrlEncoded
    @POST("master")
    Call<ModelStatus> doGetMasterStatus(@Field("TableName") String TableName, @Header("Authorization") String authHeader);

    @Multipart
    @POST("createTiket")
    Call<ModelCreateTiket> uploadMultipleFilesDynamic(@PartMap() Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> files, @Header("Authorization") String authHeader);

    @Multipart
    @POST("reply")
    Call<ModelContentTiket> doPostReplyTiket(@PartMap() Map<String, RequestBody> partMap, @Part List<MultipartBody.Part> files, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("master")
    Call<ModelContentTiket> doGetContentTiket(@Field("TableName") String TableName, @Field("tiketId") String id, @Header("Authorization") String authHeader);
}
