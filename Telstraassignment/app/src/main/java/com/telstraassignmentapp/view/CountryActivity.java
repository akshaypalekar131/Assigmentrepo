
package com.telstraassignmentapp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.telstraassignmentapp.R;

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

