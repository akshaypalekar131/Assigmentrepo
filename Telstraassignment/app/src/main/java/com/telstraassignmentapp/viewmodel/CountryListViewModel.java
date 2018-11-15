package com.telstraassignmentapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.telstraassignmentapp.apiinterface.ApiClient;
import com.telstraassignmentapp.model.ApiResponse;

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


