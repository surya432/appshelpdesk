package com.surya432.skripsi.Helpers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.surya432.skripsi.Activity.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIHelper {
    public static <T> void enqueueWithRetry(Context context, Call<T> call, final Callback<T> callback) {
        call.enqueue(new RetryableCallback<T>(call) {

            @Override
            public void onFinalResponse(Call<T> call, Response<T> response) {
                if(response.code() == 401){
                    SessionManager sessionManager = new SessionManager(context);
                    sessionManager.destroySession();
                    Intent intent = new Intent(context, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }else {
                    Log.d("APIHelper", "reached onFinalResponse");
                    callback.onResponse(call, response);
                }
            }

            @Override
            public void onFinalFailure(Call<T> call, Throwable t) {
                Log.d("APIHelper", "reached onFinalFailure");
                callback.onFailure(call, t);
            }
        });
    }

    public static boolean isCallSuccess(Response response) {
        int code = response.code();
        return (code >= 200 && code < 400);
    }
}
