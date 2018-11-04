package com.telstraassignmentapp.viewmodel;

import android.app.Application;

import com.telstraassignmentapp.apiinterface.ApiClient;
import com.telstraassignmentapp.model.ApiResponse;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CountryListViewModel extends AndroidViewModel {

    private final LiveData<ApiResponse> apiResponseLiveData;

    public CountryListViewModel(Application application) {
        super(application);
        apiResponseLiveData = ApiClient.getInstance().getApiResponse();
    }


    public LiveData<ApiResponse> getApiResponseLiveData() {
        return apiResponseLiveData;
    }
}


