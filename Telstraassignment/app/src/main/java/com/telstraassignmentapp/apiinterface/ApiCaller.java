package com.telstraassignmentapp.apiinterface;

import com.telstraassignmentapp.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCaller {

    String baseUrl = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    String factsUrl = "facts.json";

    @GET(factsUrl)
    Call<ApiResponse> getApiResponse();
}
