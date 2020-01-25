package com.oxxo.heroehello.Module.Start.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mno.init.Core.View.BaseRecyclerAdapter;
import com.oxxo.heroehello.Module.Start.Response.CharactersResponse;
import com.oxxo.heroehello.R;


public class CharactersAdapter extends BaseRecyclerAdapter<CharactersResponse.Results> {

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
        return new ViewHolder(view);
    }

    private class ViewHolder extends RecyclerViewHolder<CharactersResponse.Results> {

        TextView nombre;
        ImageView thumb;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre = getUi().getTextView(R.id.nombre);
            thumb = getUi().getImageView(R.id.thumb);

        }

        @Override
        public void setItem(CharactersResponse.Results heroe, int position) {

            nombre.setText(heroe.getName());

            String path = heroe.getThumbnail().getPath().replace("http://", "https://");
            String image = path + "/portrait_medium." + heroe.getThumbnail().getExtension();
            Glide.with(getUi().getContext()).load(image).into(thumb);

        }

    }




}
