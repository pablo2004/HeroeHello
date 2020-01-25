package com.mno.init.Core.Security;

/**
 * Created by pablo on 23/08/17.
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import com.mno.init.Core.Module.ActivityBase;
import com.mno.init.Core.Object.MLog;


public class KeyPinStore {

    private ActivityBase activity = null;
    private static KeyPinStore instance = null;
    private SSLContext sslContext = null;

    public static synchronized KeyPinStore getInstance(ActivityBase activity) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException{
        if (instance == null){
            instance = new KeyPinStore(activity);
        }
        return instance;
    }

    public Certificate getCertificateFromFile(String key){

        Certificate ca = null;
        CertificateFactory cf = null;
        InputStream caInput = null;

        if(activity != null) {

            try {
                cf = CertificateFactory.getInstance("X.509");
            } catch (CertificateException e) {
                MLog.e("Certificate", e.getMessage());
            }

            try {
                caInput = new BufferedInputStream(activity.getAssets().open(key));
                ca = cf.generateCertificate(caInput);
                caInput.close();
            } catch (IOException e) {
                MLog.e("IoException", e.getMessage());
            } catch (CertificateException e) {
                MLog.e("Certificate", e.getMessage());
            }

        }

        return ca;
    }

    private KeyPinStore(ActivityBase activity) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException{
        // https://developer.android.com/training/articles/security-ssl.html
        // Load CAs from an InputStream

        // Create a KeyStore containing our trusted CAs
        this.activity = activity;
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);

        Certificate productionCa = getCertificateFromFile("production.cer");
        Certificate productionCaCrt = getCertificateFromFile("production.crt");
        //Certificate devCa = getCertificateFromFile("dev.cer");
        //Certificate qaCa = getCertificateFromFile("qa.cer");
        Certificate pruebaCa = getCertificateFromFile("prueba.cer");
        Certificate actinverCa = getCertificateFromFile("actinver.cer");

        if(productionCa != null) {
            keyStore.setCertificateEntry("production1", productionCa);
        }

        if(productionCaCrt != null) {
            keyStore.setCertificateEntry("production2", productionCaCrt);
            MLog.e("loadPr2", "todo_ok");
        }

        if(pruebaCa != null) {
            keyStore.setCertificateEntry("prueba", pruebaCa);
        }

        if(actinverCa != null) {
            keyStore.setCertificateEntry("actinver", actinverCa);
        }

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
        // SSLContext context = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);
    }

    public SSLContext getContext(){
        return sslContext;
    }

}