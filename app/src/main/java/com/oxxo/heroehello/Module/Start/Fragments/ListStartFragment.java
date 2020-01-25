package com.oxxo.heroehello.Module.Start.Fragments;

import androidx.recyclerview.widget.RecyclerView;

import com.mno.init.Core.View.BaseRecyclerAdapter;
import com.mno.init.Core.View.Ui;
import com.oxxo.heroehello.Config.Config;
import com.oxxo.heroehello.Config.MainFragment;
import com.oxxo.heroehello.Config.WebService.MarvelWS;
import com.oxxo.heroehello.Module.Start.Adapters.CharactersAdapter;
import com.oxxo.heroehello.Module.Start.Response.CharactersResponse;
import com.oxxo.heroehello.Module.Start.StartActivity;
import com.oxxo.heroehello.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListStartFragment extends MainFragment {

    private RecyclerView list;

    public ListStartFragment() {
        setViewId(R.id.fragmentStart);
        setLayoutView(R.layout.start_fragment_list);
    }

    @Override
    public boolean onBack(){
        getActivityBase().finish();
        return false;
    }

    @Override
    public void onViewReady(){

        list = getUi().getRecyclerView(R.id.list);
        getHeroes();

    }

    private void getHeroes(){

        getActivityBase().getProgress().showWidget("getHeroes");
        MarvelWS ws = getMainActivity().getWebService();

        long ts = System.currentTimeMillis();

        ws.characters(Config.WS_PUBLIC_KEY, getHash(ts), ts).enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {

                if(response != null && response.isSuccessful()){

                    CharactersResponse resp  = response.body();

                    CharactersAdapter adapter = new CharactersAdapter();
                    adapter.setItems(resp.getData().getResults());
                    adapter.setItemSelectedListener(new BaseRecyclerAdapter.ItemSelectedListener<CharactersResponse.Results>() {
                        @Override
                        public void onItemSelected(CharactersResponse.Results results, Ui ui, int position, BaseRecyclerAdapter<CharactersResponse.Results> adapter) {

                            StartActivity.setCharacter(results);
                            DetailStartFragment detail = new DetailStartFragment();
                            detail.transaction(getSelf(), true);

                        }
                    });

                    list.setLayoutManager(getActivityBase().getLinearLayoutManagerVertical());
                    list.setHasFixedSize(true);
                    list.setAdapter(adapter);

                }

                getActivityBase().getProgress().dismissWidget("getHeroes");
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable t) {
                call.cancel();
                getActivityBase().getProgress().dismissWidget("getHeroes");
            }
        });

    }


}
