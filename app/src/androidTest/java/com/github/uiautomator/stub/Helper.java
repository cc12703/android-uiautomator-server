package com.github.uiautomator.stub;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by hzsunshx on 2017/9/5.
 */

public class Helper {


    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null) {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                System.out.println("app:" + processInfo.processName);
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isServiceRunning(final Context context, String serviceClassName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        for (ActivityManager.RunningServiceInfo runningServiceInfo : services) {
            System.out.println("service:" + runningServiceInfo.service.getClassName());
            if (runningServiceInfo.service.getClassName().equals(serviceClassName)) {
                return true;
            }
        }
        return false;
    }

    public static android.net.Network reqNetByCellular(Context ctx) {
        Log.i("req network by cellular");

        final CountDownLatch latch = new CountDownLatch(1);
        final android.net.Network[] ret = {null};
        final ConnectivityManager cMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest req = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build();
        cMgr.requestNetwork(req, new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(android.net.Network network) {
                super.onAvailable(network);
                Log.d("network: onAvailable" + network);
                ret[0] = network;
                latch.countDown();
            }

            public void onUnavailable() {
                super.onUnavailable();
                Log.d("network: onUnavailable");
                latch.countDown();
            }
        });


        try {
            latch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ret[0];
    }
}
