package com.example.cloneamazonapplication.adapter;

import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloneamazonapplication.modeldata.Cart;

public class QuantityAdapter extends ListAdapter<Cart,QuantityAdapter.QuantityViewHolder> {




    protected QuantityAdapter(@NonNull DiffUtil.ItemCallback<Cart> diffCallback) {
        super(diffCallback);
    }

    protected QuantityAdapter(@NonNull AsyncDifferConfig<Cart> config) {
        super(config);
    }

    @NonNull
    @Override
    public QuantityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull QuantityViewHolder holder, int position) {

    }

    public class QuantityViewHolder extends RecyclerView.ViewHolder {
    }
}
