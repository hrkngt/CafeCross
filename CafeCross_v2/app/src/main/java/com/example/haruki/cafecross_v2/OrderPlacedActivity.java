package com.example.haruki.cafecross_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderPlacedActivity extends AppCompatActivity {

    TextView tvMessage;
    Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_back_layout);
        tvMessage = findViewById(R.id.tvMessage);
        btBack = findViewById(R.id.btBack);
        tvMessage.setText(R.string.thanks_for_order);
        btBack.setText(R.string.go_back_to_main);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderPlacedActivity.this, MainActivity.class);
                OrderPlacedActivity.this.startActivity(intent);
            }
        });






    }
}
