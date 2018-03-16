package com.example.haruki.cafecross_v2;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartViewActivity extends AppCompatActivity {

    RecyclerView cartRecycler;
    CartItemAdapter cartItemAdapter;
    List<Product> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

        cartItemList = new ArrayList<>();
        cartRecycler = (RecyclerView)findViewById(R.id.cart_recycler_view);
        cartRecycler.setHasFixedSize(true);
        cartRecycler.setLayoutManager(new LinearLayoutManager(this));
        cartItemList = ShoppingCartHelper.getCartList();
        cartItemAdapter = new CartItemAdapter(CartViewActivity.this, cartItemList);
        cartRecycler.setAdapter(cartItemAdapter);

        if(cartItemList.size() == 0){
            //Shopping cart Empty
            setContentView(R.layout.go_back_layout);
            TextView tvMessage = findViewById(R.id.tvMessage);
            Button btBack = (Button)findViewById(R.id.btBack);

            tvMessage.setText(R.string.no_item);
            btBack.setText(R.string.go_back_to_shopping);

            btBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CartViewActivity.this, MainActivity.class);
                    CartViewActivity.this.startActivity(intent);
                }
            });
        }else {
            Button btGoProceed = (Button) findViewById(R.id.btGoProceed);
            btGoProceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(CartViewActivity.this, ProceedActivity.class);
                    CartViewActivity.this.startActivity(intent1);
                }
            });
        }


    }


}
