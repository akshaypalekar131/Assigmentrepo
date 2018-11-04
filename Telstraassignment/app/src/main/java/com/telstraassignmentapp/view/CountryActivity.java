package com.telstraassignmentapp.view;

import android.os.Bundle;

import com.telstraassignmentapp.R;
import com.telstraassignmentapp.model.ApiResponse;
import com.telstraassignmentapp.viewmodel.CountryHeaderViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class CountryActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);


            CountryListFragment countryListFragment = new CountryListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, countryListFragment, CountryListFragment.TAG).commit();

    }

}

