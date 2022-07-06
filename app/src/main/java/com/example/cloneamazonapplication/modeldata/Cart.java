package com.example.cloneamazonapplication.modeldata;

import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class Cart {

    private String pid, name, price,quantity;

    public Cart(String price, int quantity) {
    }

    public Cart(String pid, String name, String price,String quantity) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.quantity=quantity;

    }
    public String getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartItem{" +
                "price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cartItem = (Cart) o;
        return getQuantity() == cartItem.getQuantity() &&
                getPrice().equals(cartItem.getPrice());
    }





    @BindingAdapter("android:setVal")
    public static void getSelectedSpinnerView(Spinner spinner,int quantity){
        spinner.setSelection(quantity,true);
    }

    public static DiffUtil.ItemCallback<Cart> itemCallback = new DiffUtil.ItemCallback<Cart>() {
        @Override
        public boolean areItemsTheSame(@NonNull Cart oldItem, @NonNull Cart newItem) {
            return oldItem.getQuantity() == newItem.getQuantity();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Cart oldItem, @NonNull Cart newItem) {
           return oldItem.equals(newItem);
        }
    };





}
