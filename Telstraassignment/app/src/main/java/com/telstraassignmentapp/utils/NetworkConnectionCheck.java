package com.telstraassignmentapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnectionCheck {

    private static NetworkConnectionCheck networkConnectionCheck;

    private NetworkConnectionCheck(){

    }

    public static NetworkConnectionCheck getNetworkConnectionCheck() {
        if(networkConnectionCheck == null){

            networkConnectionCheck = new NetworkConnectionCheck();
        }

        return networkConnectionCheck;
    }

    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

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
