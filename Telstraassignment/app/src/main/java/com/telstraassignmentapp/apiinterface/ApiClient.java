package com.telstraassignmentapp.apiinterface;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.telstraassignmentapp.model.ApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static ApiClient apiClient;
    private ApiCaller apiCaller;

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiCaller.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCaller = retrofit.create(ApiCaller.class);
    }

    public synchronized static ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
        }

        return apiClient;
    }

    public LiveData<ApiResponse> getApiResponse() {
        final MutableLiveData<ApiResponse> mutableLiveData = new MutableLiveData<>();

        apiCaller.getApiResponse().enqueue(new Callback<ApiResponse>() {

            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                mutableLiveData.setValue(null);
            }


        });

        return mutableLiveData;
    }
}
