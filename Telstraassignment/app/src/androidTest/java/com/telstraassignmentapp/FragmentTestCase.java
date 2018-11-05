package com.telstraassignmentapp;

import com.telstraassignmentapp.view.CountryActivity;
import com.telstraassignmentapp.view.CountryListFragment;

import org.junit.Rule;
import org.junit.Test;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.test.rule.ActivityTestRule;

public class FragmentTestCase {

    @Rule
    public ActivityTestRule<CountryActivity> countryActivityActivityTestRule = new ActivityTestRule<CountryActivity>(CountryActivity.class);

    @Test

    public void testView(){
        countryActivityActivityTestRule.getActivity().getSupportFragmentManager().findFragmentByTag("CountryListFragment");
    }

}
