package com.example.haruki.cafecross_v2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfirmationActivity extends AppCompatActivity {

    RecyclerView cartRecycler;
    ConfirmationCartItemAdapter cartItemAdapter;
    List<Product> cartItemList;

    TextView tvName;
    TextView tvZip;
    TextView tvPrefecture;
    TextView tvAddress1;
    TextView tvAddress2;
    TextView tvApartment;
    TextView tvCompany;
    TextView tvEmail;
    TextView tvPhone;

    TextView tvFinalTotalPrice;
    EditText etComments;
    Button btConfirm;

    final String TAG = "CONFIRMATIONACTIVITY: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        cartItemList = new ArrayList<>();
        cartRecycler = (RecyclerView)findViewById(R.id.confirmation_item_recycler_view);
        cartRecycler.setHasFixedSize(true);
        cartRecycler.setLayoutManager(new LinearLayoutManager(this));
        cartItemList = ShoppingCartHelper.getCartList();

        cartItemAdapter = new ConfirmationCartItemAdapter(ConfirmationActivity.this, cartItemList);
        cartRecycler.setNestedScrollingEnabled(false);
        cartRecycler.setAdapter(cartItemAdapter);


        //Get customer information from the previous activity
        Intent intentGet = getIntent();
        final String name = intentGet.getStringExtra("name");
        final String zip = intentGet.getStringExtra("zip");
        final String prefecture = intentGet.getStringExtra("prefecture");
        final String address1 = intentGet.getStringExtra("address1");
        final String address2 = intentGet.getStringExtra("address2");
        final String apartment = intentGet.getStringExtra("apartment");
        final String company = intentGet.getStringExtra("company");
        final String email = intentGet.getStringExtra("email");
        final String phone = intentGet.getStringExtra("phone");

        tvName = findViewById(R.id.tvName);
        tvZip = findViewById(R.id.tvZip);
        tvPrefecture = findViewById(R.id.tvPrefecture);
        tvAddress1 = findViewById(R.id.tvAddress1);
        tvAddress2 = findViewById(R.id.tvAddress2);
        tvApartment = findViewById(R.id.tvApartment);
        tvCompany = findViewById(R.id.tvCompany);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvFinalTotalPrice = findViewById(R.id.tvFinalTotalPrice);
        etComments = findViewById(R.id.etComment);
        btConfirm = findViewById(R.id.btConfirm);

        tvName.setText(name);
        tvZip.setText(zip);
        tvPrefecture.setText(prefecture);
        tvAddress1.setText(address1);
        tvAddress2.setText(address2);
        tvApartment.setText(apartment);
        tvCompany.setText(company);
        tvEmail.setText(email);
        tvPhone.setText(phone);

        final String finalTotal = "¥ " + String.valueOf(calculateTotalPrice());
        final String orderedItems = concatOrder(calculateTotalPrice());



        tvFinalTotalPrice.setText(finalTotal);

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = etComments.getText().toString().trim();
                if(temp.equals("")){
                    temp = "---";
                }
                String comments = temp;
                sendOrderRequest(name, zip, prefecture, address1, address2, apartment, company, email, phone, orderedItems, comments);
            }
        });

    }

    int calculateTotalPrice() {
        int finalTotal = 0;
        for (Product p : cartItemList) {
            finalTotal += (p.getPrice() * ShoppingCartHelper.getProductQuantity(p));
        }
        return finalTotal;
    }

    String concatOrder(int finalTotal){
        String temp = "";
        for(Product p : ShoppingCartHelper.getCartList()){
            temp += p.getProduct_name();
            temp += "@"+ String.valueOf(ShoppingCartHelper.getProductQuantity(p)) + "///";
        }
        temp += "¥ " + String.valueOf(finalTotal);

        return temp;
    }



    private void sendOrderRequest(String name, String zip, String prefecture, String address1, String address2,
                                  String apartment, String company, String email, String phone, String orderedItems, String comments){

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Intent loginIntent = new Intent(ConfirmationActivity.this, OrderPlacedActivity.class);
                        ConfirmationActivity.this.startActivity(loginIntent);


                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmationActivity.this);
                        builder.setMessage("エラーが発生しました。\n" +
                                "インターネットの接続状況を確認し、改善されない場合は大変お手数ですが、\n" +
                                " haruki.higuchi1994@gmail.com\nまでご連絡ください。").setNegativeButton("OK", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        SendOrderRequest request = new SendOrderRequest(name, zip, prefecture, address1, address2,
                apartment, company, email, phone, orderedItems, comments, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ConfirmationActivity.this);
        queue.add(request);
    }




}
