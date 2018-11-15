package com.telstraassignmentapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.telstraassignmentapp.view.CountryActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class ActivityTestCase {

    @Rule
    public ActivityTestRule<CountryActivity> countryActivityActivityTestRule = new ActivityTestRule<CountryActivity>(CountryActivity.class);

    @Test
    public void checkView(){
        //onView(withId(R.id.fragment_container));
    }
}
