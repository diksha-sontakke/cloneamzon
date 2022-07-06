package com.example.cloneamazonapplication.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cloneamazonapplication.R;
import com.example.cloneamazonapplication.adapter.ProductAdapter;
import com.example.cloneamazonapplication.adapter.ViewPagerAdapter;
import com.example.cloneamazonapplication.constant.Constant;
import com.example.cloneamazonapplication.modeldata.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {


    Toolbar toolbar;

    ViewPager viewPager;
    int[] images={R.drawable.one,R.drawable.two,R.drawable.three};
    ViewPagerAdapter viewPagerAdapter;

    CardView shoes1,shoes2,shoes3,shoes4,shoes5;
    TextView oddShoeName, oddShoePrice,evenShoeName,evenShoePrice;
    TextView viewAllProducts;

    FirebaseStorage storage;
    StorageReference storageReference;

    ImageView home_cart;

    //for image to change for the cart
    Intent intentCart;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        shoes1=findViewById(R.id.shoes1);
        shoes2=findViewById(R.id.shoes2);
        shoes3=findViewById(R.id.shoes3);
        shoes4=findViewById(R.id.shoes4);
        shoes5=findViewById(R.id.shoes5);


        oddShoeName=findViewById(R.id.oddShoeName);
        oddShoePrice=findViewById(R.id.oddShoePrice);
        evenShoeName=findViewById(R.id.evenShoeName);
        evenShoePrice=findViewById(R.id.evenShoePrice);
        viewAllProducts=findViewById(R.id.viewAllProducts);

        toolbar=findViewById(R.id.toolbar);

        storage=FirebaseStorage.getInstance();
        
        home_cart=findViewById(R.id.home_cart);






        //for slider view
        viewPager=(ViewPager) findViewById(R.id.viewPagerMain);
        viewPagerAdapter= new ViewPagerAdapter(HomeActivity.this, images);
        viewPager.setAdapter(viewPagerAdapter);

        menuMethod();

        shoeClicked();

        viewAll();
        imageCart();

        listViewProducts();
        addingToProduct();
        changeCartImg();
    }


    public void menuMethod() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.home:

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
                        startActivity(new Intent(getApplicationContext(), MyCartBagActivity.class));
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;





                }


                return true;
            }
        });
    }

    private  void  shoeClicked(){
        shoes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,ProductDetailsActivity.class  );
                i.putExtra("name", oddShoeName.getText().toString());
                i.putExtra("category", "Men's  Outdoor Shoe");
                i.putExtra("price", oddShoePrice.getText().toString());
                i.putExtra("uniqueId", oddShoeName.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });
        shoes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,ProductDetailsActivity.class  );
                i.putExtra("name", evenShoeName.getText().toString());
                i.putExtra("category", "Men's Running Shoe");
                i.putExtra("price",evenShoePrice.getText().toString());
                i.putExtra("uniqueId", evenShoeName.getText().toString());
                i.putExtra("id", 2);
                startActivity(i);
            }
        });

        shoes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,ProductDetailsActivity.class  );
                i.putExtra("name", oddShoeName.getText().toString());
                i.putExtra("category", "Men's  Outdoor Shoe");
                i.putExtra("price", oddShoePrice.getText().toString());
                i.putExtra("uniqueId", oddShoeName.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });
        shoes4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,ProductDetailsActivity.class  );
                i.putExtra("name", evenShoeName.getText().toString());
                i.putExtra("category", "Men's Running Shoe");
                i.putExtra("price",evenShoePrice.getText().toString());
                i.putExtra("uniqueId", evenShoeName.getText().toString());
                i.putExtra("id", 2);
                startActivity(i);
            }
        });

        shoes5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,ProductDetailsActivity.class  );
                i.putExtra("name", oddShoeName.getText().toString());
                i.putExtra("category", "Men's  Outdoor Shoe");
                i.putExtra("price", oddShoePrice.getText().toString());
                i.putExtra("uniqueId", oddShoeName.getText().toString());
                i.putExtra("id", 1);
                startActivity(i);
            }
        });

    }

    private void viewAll(){

        viewAllProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }


    private void imageCart(){
        home_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MyCartBagActivity.class);
                startActivity(intent);
            }
        });



    }

    private  void listViewProducts(){

        ListView lvProducts = (ListView) findViewById(R.id.productList);

        ProductAdapter productAdapter = new ProductAdapter(this);
        productAdapter.updateProducts(Constant.PRODUCT_LIST);

        lvProducts.setAdapter(productAdapter);

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //basically we are going to the product class and getting that perticular
                //item index
                Product product = Constant.PRODUCT_LIST.get(position);
                Intent intent = new Intent(HomeActivity.this, ProductDetailsActivity.class);

                //id 3 because we took the id evenshoe i.e shoe2 listener as 2 and oddshoe i.e shoe1 as 1
                intent.putExtra("id", 3);
                intent.putExtra("uniqueId", product.getpName());
                intent.putExtra("name", product.getpName());
                intent.putExtra("description", product.getpDescription());
                intent.putExtra("category", "Smartphone");
                intent.putExtra("pprice", Constant.CURRENCY + String.valueOf(product
                        .getpPrice().setScale(0, BigDecimal.ROUND_HALF_UP)));
               // intent.putExtra("quantity", product.getpQuantity());
                intent.putExtra("imageName", product.getpImageName());
                intent.putExtra("quantity", product.getpQuantity());

                //
                Log.d("TAG", "View product: " + product.getpName());
                startActivity(intent);
            }
        });



    }

    private void addingToProduct(){
        String saveCurrentDate,saveCurrentTime;
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        DatabaseReference prodListRef = FirebaseDatabase.getInstance().getReference().child("View All");

        //since it gives database error for  android:text="Nike Air Max \nOutdoor Shoe" \n
        //we gonna give space in xml abd then replace it with \n with this close
       // String name=evenShoeName.getText().toString().replaceAll("\n"," ");

        String name="Nike Air Max Running Shoe";
        final HashMap<String, Object> prodMap = new HashMap<>();
        prodMap.put("pid",name );
        prodMap.put("name", name);
        prodMap.put("price", "₹7037");
        prodMap.put("category", "SmartPhone");
        prodMap.put("description","6 GB RAM\n128 GB ROM\nExpandable Upto 1 TB");


        prodMap.put("date", saveCurrentDate);
        prodMap.put("time", saveCurrentTime);
        prodListRef.child("User View").child("Products")
                .child(name).updateChildren(prodMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.i("Task","successfull");

                        }
                    }
                });



    }

    //to change the image of cart after adding product

    private void changeCartImg(){

        //productdetailactivity for refernce 1
        intentCart = getIntent();
        if (intentCart.getStringExtra("cartAdd") != null && intentCart.getStringExtra("cartAdd").equals("yes")) {
            home_cart.setImageResource(R.drawable.cart_notif);
        } else if (intentCart.getStringExtra("cartAdd") != null && intentCart.getStringExtra("cartAdd").equals("no")) {
            home_cart.setImageResource(R.drawable.cart);
        } else {
            home_cart.setImageResource(R.drawable.cart);
        }



    }




}