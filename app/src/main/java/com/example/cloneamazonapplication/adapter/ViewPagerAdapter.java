package com.example.cloneamazonapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cloneamazonapplication.R;

import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    //adapter for the ViewPager that we have created


    Context context;
    int[] images;

    LayoutInflater mLayoutInflater;

    public ViewPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        mLayoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }




    @Override
    public int getCount() {
        return images.length;
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position){
        //remove @nonNull form ViewGroup
        //inflating slider_view.xml


        //error mLayoutInflater.inflate(R.layout.slider_view,false);

        View itemView = mLayoutInflater.inflate(R.layout.slider_view,container,false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);
        // setting the image in the imageView
        imageView.setImageResource(images[position]);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);

    }
}
