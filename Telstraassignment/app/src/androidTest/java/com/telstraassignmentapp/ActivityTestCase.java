package com.telstraassignmentapp;

import com.telstraassignmentapp.view.CountryActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class ActivityTestCase {

    @Rule
    public ActivityTestRule<CountryActivity> countryActivityActivityTestRule = new ActivityTestRule<CountryActivity>(CountryActivity.class);

    @Test
    public void checkView(){
        onView(withId(R.id.fragment_container));
    }
}
