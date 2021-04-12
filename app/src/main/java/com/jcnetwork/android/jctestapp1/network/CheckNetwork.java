package com.jcnetwork.android.jctestapp1.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Class to check internet connection
 */

public class CheckNetwork {

    /**
     * Return boolean value if you have internet connection (true) or not (false)
     * @param context call from current context
     * @return boolean
     */
    // TODO Test if it works for both wifi and mobile data
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr != null) {
            NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    return true;
                } else
                    return activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            }
        }
        // Not connected
        return false;
    }
}

