package com.mno.init.Core.Object;

import android.util.Log;

/**
 * Created by pablo on 5/07/18.
 */

public class MLog {

    private static final boolean isProducction = false;

    public static boolean isProducction() {
        return isProducction;
    }

    public static void e(String tag, String msg){

        if(!isProducction()){
            Log.e(tag, msg);
        }

    }

    public static void d(String tag, String msg){

        if(!isProducction()){
            Log.d(tag, msg);
        }

    }

    public static void i(String tag, String msg){

        if(!isProducction()){
            Log.i(tag, msg);
        }

    }

    public static void v(String tag, String msg){

        if(!isProducction()){
            Log.v(tag, msg);
        }

    }

    public static void longLog(String tag, String msg){

        if(!isProducction()) {

            int maxLogSize = msg.length();
            for (int i = 0; i <= msg.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i + 1) * maxLogSize;
                end = end > msg.length() ? msg.length() : end;
                Log.e(tag, msg.substring(start, end));
            }

        }

    }

}
