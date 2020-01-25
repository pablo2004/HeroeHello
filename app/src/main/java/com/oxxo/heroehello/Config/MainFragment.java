package com.oxxo.heroehello.Config;

import com.mno.init.Core.Module.FragmentBase;
import java.io.UnsupportedEncodingException;

public class MainFragment extends FragmentBase {

    public MainActivity getMainActivity (){
        return (MainActivity) getActivityBase();
    }

    public String MD5(String md5) {

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){ }

        return null;
    }

    public String getHash(long ts){
        return MD5(ts+Config.WS_PRIVATE_KEY+Config.WS_PUBLIC_KEY);
    }

}
