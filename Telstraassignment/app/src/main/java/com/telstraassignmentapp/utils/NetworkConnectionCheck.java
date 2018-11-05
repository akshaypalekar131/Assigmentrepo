package com.telstraassignmentapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class NetworkConnectionCheck {

    private static NetworkConnectionCheck networkConnectionCheck;

    private NetworkConnectionCheck() {

    }

    public static NetworkConnectionCheck getNetworkConnectionCheck() {
        if (networkConnectionCheck == null) {

            networkConnectionCheck = new NetworkConnectionCheck();
        }

        return networkConnectionCheck;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            Network[] networks = connectivity.getAllNetworks();
            if(networks!=null) {
                NetworkInfo networkInfo;
                for (Network network : networks) {
                    networkInfo = connectivity.getNetworkInfo(network);
                    if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }


}
