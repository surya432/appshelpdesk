package com.surya432.skripsi.API;

import com.surya432.skripsi.Model.ModelListTiket;
import com.surya432.skripsi.Model.ModelLogin;
import com.surya432.skripsi.Model.ModelStatus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface RestApi {
    @FormUrlEncoded
    @POST("login")
    Call<ModelLogin> doLogin(@Field("email") String Username, @Field("password") String Password);

    @FormUrlEncoded
    @POST("master")
    Call<ModelListTiket> doGetListTiket(@Field("TableName") String TableName, @Header("Authorization") String authHeader);

    @FormUrlEncoded
    @POST("master")
    Call<ModelStatus> doGetMasterStatus(@Field("TableName") String TableName, @Header("Authorization") String authHeader);
}
