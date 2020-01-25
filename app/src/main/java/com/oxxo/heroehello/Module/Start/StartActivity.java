package com.oxxo.heroehello.Module.Start;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.oxxo.heroehello.Config.Config;
import com.oxxo.heroehello.Config.MainActivity;
import com.oxxo.heroehello.Config.MainFragment;
import com.oxxo.heroehello.Config.WebService.MarvelWS;
import com.oxxo.heroehello.Module.Start.Adapters.CharactersAdapter;
import com.oxxo.heroehello.Module.Start.Fragments.DetailStartFragment;
import com.oxxo.heroehello.Module.Start.Fragments.ListStartFragment;
import com.oxxo.heroehello.Module.Start.Response.CharactersResponse;
import com.oxxo.heroehello.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends MainActivity {

    private static CharactersResponse.Results character = null;

    public StartActivity() {
        setActivityLayout(R.layout.start_activity);
    }

    public static CharactersResponse.Results getCharacter() {
        return StartActivity.character;
    }

    public static void setCharacter(CharactersResponse.Results character) {
        StartActivity.character = character;
    }

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);

        getProgress().addWidget("getHeroes", getString(R.string.action_wait), getString(R.string.action_loading));

        getUi().getImageView(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if(getSupportFragmentManager().getFragments().size() == 0){
            ListStartFragment start = new ListStartFragment();
            start.transaction(getSelf(), null, true);
        }

    }

}
