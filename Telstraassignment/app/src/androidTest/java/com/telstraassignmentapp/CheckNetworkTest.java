package com.telstraassignmentapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;


public class CheckNetworkTest {

    @Test
    public boolean checkInternetConnection(){

        Context appContext = InstrumentationRegistry.getTargetContext();

        ConnectivityManager connectivity = (ConnectivityManager)appContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null) {

                for (NetworkInfo anInfo : info) {

                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {

                        return true;
                    }
                }
            }
        }

        return false;

    }

}
