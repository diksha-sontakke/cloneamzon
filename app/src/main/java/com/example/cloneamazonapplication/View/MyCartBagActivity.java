package com.example.cloneamazonapplication.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cloneamazonapplication.R;
import com.example.cloneamazonapplication.databinding.CartItemsRecyclerDataBinding;
import com.example.cloneamazonapplication.interfaces.CartQuantityInterface;
import com.example.cloneamazonapplication.modeldata.Cart;
import com.example.cloneamazonapplication.viewholder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyCartBagActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppCompatButton nextBtn;
    RecyclerView.LayoutManager layoutManager;
    TextView totalPrice;
    private int overallPrice=0;

    CartItemsRecyclerDataBinding cartItemsRecyclerDataBinding;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart_bag);

        auth=FirebaseAuth.getInstance();

        recyclerView=findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        nextBtn=findViewById(R.id.next_button);
        totalPrice=findViewById(R.id.totalprice);




        menuMethod();
        nextButton();
    }

    public void menuMethod() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.my_cart);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(),AddActivity.class));
                        overridePendingTransition(0, 0);

                        return true;


                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.my_cart:

                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;



                }

                //true before
                return true;
            }
        });
    }

    private void nextButton(){
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalPrice.getText().toString().equals("₹0") || totalPrice.getText().toString().length()==0) {
                    Toast.makeText(MyCartBagActivity.this, "Cannot place order with 0 items", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MyCartBagActivity.this, PlaceOrderActivity.class);
                    intent.putExtra("totalAmount", totalPrice.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

    private void spinnerQuanatity(){




    }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference cartListRef= FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options= new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User View").child(auth.getCurrentUser().getUid())
                        .child("Products"),Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter=
                new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {


                    @NonNull
                    @Override
                    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_recycler_data,parent,false);
                        CartViewHolder holder=new CartViewHolder(view);

                       // DataBindingUtil.setContentView(MyCartBagActivity.this, R.layout.cart_items_recycler_data);


                        return holder;
                    }


                    @Override
                    protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull Cart model) {




                        String name=model.getName().replaceAll("\n"," ");
                        holder.cartProductName.setText(name);
                        holder.cartProductPrice.setText(model.getPrice());

                        //to replace old price to new price
                        String intPrice= model.getPrice().replace("₹","");
                        overallPrice+=Integer.valueOf(intPrice);
                        totalPrice.setText("₹"+String.valueOf(overallPrice));

                        //when tap on cart list
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //to delete item form cart
                                AlertDialog.Builder builder=new AlertDialog.Builder(MyCartBagActivity.this);
                                builder.setTitle("Delete item");
                                builder.setMessage("Do you want to remove this product from cart?")
                                        .setCancelable(false)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //after deleting updating the db
                                                cartListRef.child("User View")
                                                        .child(auth.getCurrentUser().getUid())
                                                        .child("Products")
                                                        .child(model.getPid())
                                                        .removeValue()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful()){
                                                                    String intPrice= model.getPrice().replace("₹","");
                                                                    overallPrice-=Integer.valueOf(intPrice);
                                                                    totalPrice.setText("₹"+String.valueOf(overallPrice));
                                                                    Toast.makeText(MyCartBagActivity.this, "Item removed successfully", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });

                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //  Action for 'NO' Button
                                                dialog.cancel();
                                            }
                                        });
                                builder.show();
                            }
                        });


                    }

                };

                    recyclerView.setAdapter(adapter);
                    adapter.startListening();
    }


}