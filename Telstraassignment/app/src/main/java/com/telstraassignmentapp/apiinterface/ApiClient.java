package com.telstraassignmentapp.apiinterface;


import android.widget.Toast;

import com.telstraassignmentapp.model.ApiResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private ApiCaller apiCaller;
    private static ApiClient apiClient;

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiCaller.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCaller = retrofit.create(ApiCaller.class);
    }

    public synchronized static ApiClient getInstance() {
        if (apiClient == null) {
            if (apiClient == null) {
                apiClient = new ApiClient();
            }
        }
        return apiClient;
    }

    public LiveData<ApiResponse> getApiResponse() {
        final MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();

        apiCaller.getApiResponse().enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            mutableLiveData.setValue(null);
            }


        });

        return mutableLiveData;
    }


}
