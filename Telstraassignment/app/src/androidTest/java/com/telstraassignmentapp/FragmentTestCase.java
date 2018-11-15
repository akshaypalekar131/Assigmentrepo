package com.telstraassignmentapp;

import android.support.test.rule.ActivityTestRule;

import com.telstraassignmentapp.view.CountryActivity;

import org.junit.Rule;
import org.junit.Test;

public class FragmentTestCase {

    @Rule
    public ActivityTestRule<CountryActivity> countryActivityActivityTestRule = new ActivityTestRule<CountryActivity>(CountryActivity.class);

    @Test

    public void testView(){
        countryActivityActivityTestRule.getActivity().getSupportFragmentManager().findFragmentByTag("CountryListFragment");
    }

}
