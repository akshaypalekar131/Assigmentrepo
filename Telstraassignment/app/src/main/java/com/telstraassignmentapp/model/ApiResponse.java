package com.telstraassignmentapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CountryDetails> getCountryDetailsList() {
        return countryDetailsList;
    }

    public void setCountryDetailsList(List<CountryDetails> countryDetailsList) {
        this.countryDetailsList = countryDetailsList;
    }

    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private List<CountryDetails> countryDetailsList;



}
