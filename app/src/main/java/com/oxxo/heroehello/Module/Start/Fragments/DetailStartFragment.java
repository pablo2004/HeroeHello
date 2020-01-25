package com.oxxo.heroehello.Module.Start.Fragments;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oxxo.heroehello.Config.MainFragment;
import com.oxxo.heroehello.Module.Start.Adapters.ComicsAdapter;
import com.oxxo.heroehello.Module.Start.Response.CharactersResponse;
import com.oxxo.heroehello.Module.Start.StartActivity;
import com.oxxo.heroehello.R;

public class DetailStartFragment extends MainFragment {

    private TextView nombre;
    private ImageView thumb;
    private RecyclerView list;

    public DetailStartFragment() {
        setViewId(R.id.fragmentStart);
        setLayoutView(R.layout.start_fragment_detail);
    }

    @Override
    public boolean onBack(){

        StartActivity.setCharacter(null);

        return true;
    }

    @Override
    public void onViewReady(){

        nombre = getUi().getTextView(R.id.nombre);
        thumb = getUi().getImageView(R.id.thumb);
        list = getUi().getRecyclerView(R.id.list);

        nombre.setText(StartActivity.getCharacter().getName());

        String path = StartActivity.getCharacter().getThumbnail().getPath().replace("http://", "https://");
        String image = path + "/portrait_medium." + StartActivity.getCharacter().getThumbnail().getExtension();

        Glide.with(getMainActivity()).load(image).into(thumb);

        ComicsAdapter adapter = new ComicsAdapter();
        adapter.setItems(StartActivity.getCharacter().getComics().getItems());

        list.setLayoutManager(getActivityBase().getLinearLayoutManagerVertical());
        list.setHasFixedSize(true);
        list.setAdapter(adapter);

    }


}
