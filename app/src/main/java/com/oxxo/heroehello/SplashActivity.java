package com.oxxo.heroehello;

import android.os.Handler;

import com.oxxo.heroehello.Config.MainActivity;
import com.oxxo.heroehello.Module.Start.StartActivity;

public class SplashActivity extends MainActivity {

    public SplashActivity(){
        setActivityLayout(R.layout.splash_activity);
    }

    @Override
    public void onStart(){
        super.onStart();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                StartActivity start = new StartActivity();
                start.startAndDestroy(getSelf());

            }
        }, 2000);

    }

}
