package com.mno.init.Core.View;

import android.os.Handler;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.RecyclerViewHolder> {

    private boolean selectedItem = false;

    public interface ItemSelectedListener<T> {
        void onItemSelected(T t, Ui ui, int position, BaseRecyclerAdapter<T> adapter);
    }

    private final List<T> items = new ArrayList<>();

    private ItemSelectedListener<T> itemSelectedListener;

    public void setItems(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public T getItemAt(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItemSelectedListener(ItemSelectedListener<T> itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }
    protected void onItemSelected(int position, Ui ui) {
        if (itemSelectedListener != null) {
            itemSelectedListener.onItemSelected(getItemAt(position), ui, position, this);
        }
    }

    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.setItem(getItemAt(position), position);
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public List<T> getItems() {
        return items;
    }

    public List<T> getCopyItems() {
        return new ArrayList<>(items);
    }

    public abstract class RecyclerViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Ui ui;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ui = new Ui(itemView.getContext(), itemView);
            itemView.setOnClickListener(this);
        }

        public Ui getUi(){
            return ui;
        }

        public abstract void setItem(T t, int position);

        @Override
        public void onClick(View v) {
            if(!selectedItem) {
                selectedItem = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        selectedItem = false;
                    }
                }, 100);
                onItemSelected(getAdapterPosition(), getUi());
            }
        }

        protected T getItem() {
            return (T) getItemAt(getAdapterPosition());
        }
    }

}