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


public class ComicsAdapter extends BaseRecyclerAdapter<CharactersResponse.Items> {

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_item, parent, false);
        return new ViewHolder(view);
    }

    private class ViewHolder extends RecyclerViewHolder<CharactersResponse.Items> {

        TextView nombre;

        public ViewHolder(View itemView) {
            super(itemView);

            nombre = getUi().getTextView(R.id.nombre);

        }

        @Override
        public void setItem(CharactersResponse.Items comic, int position) {

            nombre.setText(comic.getName());

        }

    }

}
