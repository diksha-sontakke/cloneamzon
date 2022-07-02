package com.example.amazonapplication.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazonapplication.R;
import com.example.amazonapplication.interfaces.ItemClickListener;

public class RelatedProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {


    public TextView relatedProdName, relatedProdPrice;
    private ItemClickListener itemClickListener;
    public ImageView relatedProdImg;




    public RelatedProductHolder(@NonNull View itemView) {
        super(itemView);
        relatedProdName=itemView.findViewById(R.id.relatedProdName);
        relatedProdPrice=itemView.findViewById(R.id.relatedProdPrice);
        relatedProdImg=itemView.findViewById(R.id.relatedProdImg);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.OnClick(view,getAdapterPosition(),false);

    }
}
