package com.mno.init.Core.Module;

import java.util.Hashtable;

/**
 * Created by pablo on 18/07/17.
 */

public class Volatile {

    private static Hashtable<String, Hashtable<String, Object>> objectParams = new Hashtable<String, Hashtable<String, Object>>();

    public static Hashtable<String, Hashtable<String, Object>> getObjectParams() {
        return objectParams;
    }

    public static void setObjectParams(Hashtable<String, Hashtable<String, Object>> objectParams) {
        objectParams = objectParams;
    }

}
