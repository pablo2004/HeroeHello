package com.mno.init.Core.Security;

/**
 * Created by pablo on 7/03/18.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AesEncryptionData {

    @SerializedName("iv")
    @Expose
    private String iv;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("mac")
    @Expose
    private String mac;

    public AesEncryptionData(String iv, String value, String mac){
        setIv(iv);
        setValue(value);
        setMac(mac);
    }

    public String getIv() {
        return this.iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

}