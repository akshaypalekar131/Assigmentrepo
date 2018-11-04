package com.telstraassignmentapp.view;

import android.os.Bundle;

import com.telstraassignmentapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

