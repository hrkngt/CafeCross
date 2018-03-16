package com.example.haruki.cafecross_v2;


import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView productRecycler;
    ProductAdapter productAdapter;
    List<Product> productList;
    Button btNext;
    TextView tvCartPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();
        productRecycler = (RecyclerView)findViewById(R.id.recycler_view);
        productRecycler.setHasFixedSize(true);
        //Vertical RecyclerView
        productRecycler.setLayoutManager(new LinearLayoutManager(this));
        //smoothing scroll
        productRecycler.setNestedScrollingEnabled(false);

        loadAllProducts();

        btNext = (Button)findViewById(R.id.btViewCart);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartViewActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


    }

    private void loadAllProducts(){

        final String GET_ALL_PRODUCTS_URL = "http://cafecross.php.xdomain.jp/get_all_products.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, GET_ALL_PRODUCTS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            JSONArray products = jsonResponse.getJSONArray("products");

                            for(int i=0; i<products.length(); i++){
                                JSONObject detail = products.getJSONObject(i);

                                String id = detail.getString("id");
                                String product_name = detail.getString("product_name");
                                String description = detail.getString("description");
                                int price = detail.getInt("price");
                                String image = detail.getString("image");

                                Product product = new Product(id, product_name, description, price, image);
                                productList.add(product);
                            }

                            productAdapter = new ProductAdapter(MainActivity.this, productList);
                            productRecycler.setAdapter(productAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

        );

        Volley.newRequestQueue(this).add(stringRequest);
    }
}