package com.surya432.skripsi.Helpers;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class RetryableCallback<T> implements Callback<T> {
    private static final String TAG = RetryableCallback.class.getSimpleName();
    private final Call<T> call;

    public RetryableCallback(Call<T> call) {
        this.call = call;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        onFinalResponse(call, response); // no need to do any retry, pass the response and the call to the final callback
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e("RetryableCallback", t.getMessage());
        if (t.getMessage().contains("Server")) { // if error contains some keyword, retry the request as well. This is just an example to show you can call retry from either success or failure.
            retryCall();
        } else
            onFinalFailure(call, t); // if not, finish the call as a failure
    }

    public void onFinalResponse(Call<T> call, Response<T> response) { // to be overriden by calling class

    }

    public void onFinalFailure(Call<T> call, Throwable t) { // to be overriden by calling class
    }

    private void retryCall() {
        call.clone().enqueue(this); // clone the original call and enqueue it for retry
    }
}
