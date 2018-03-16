package com.example.haruki.cafecross_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProceedActivity extends AppCompatActivity {

    EditText etLastName, etFirstName;
    EditText etZip1, etZip2;
    EditText etPrefecture, etAddress1, etAddress2, etApartment;
    EditText etCompany;
    EditText etEmail, etPhone;
    Button btProceed;

    String lastName, firstName;
    String zip1, zip2;
    String prefecture, address1, address2, apartment;
    String email, phone;
    String company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceed);

        etLastName = findViewById(R.id.etLastName);
        etFirstName = findViewById(R.id.etFirstName);
        etZip1 = findViewById(R.id.etZip1);
        etZip2 = findViewById(R.id.etZip2);
        etPrefecture = findViewById(R.id.etPrefecture);
        etAddress1 = findViewById(R.id.etAddress1);
        etAddress2 = findViewById(R.id.etAddress2);
        etApartment = findViewById(R.id.etApartment);
        etCompany = findViewById(R.id.etCompany);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btProceed = findViewById(R.id.btProceed);




        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lastName = etLastName.getText().toString().trim();
                firstName = etFirstName.getText().toString().trim();
                zip1 = etZip1.getText().toString().trim();
                zip2 = etZip2.getText().toString().trim();
                prefecture = etPrefecture.getText().toString().trim();
                address1 = etAddress1.getText().toString().trim();
                address2 = etAddress2.getText().toString().trim();
                apartment = etApartment.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                company = etCompany.getText().toString().trim();

                final String fullName = lastName + " " + firstName;
                final String fullZip = zip1 + "-" + zip2;

                if(allValid()){
                    Intent intent = new Intent(ProceedActivity.this, ConfirmationActivity.class);

                    intent.putExtra("name", fullName );
                    intent.putExtra("zip", fullZip);
                    intent.putExtra("prefecture", prefecture);
                    intent.putExtra("address1", address1);
                    intent.putExtra("address2", address2);
                    intent.putExtra("apartment", apartment);
                    intent.putExtra("company", company);
                    intent.putExtra("email", email);
                    intent.putExtra("phone", phone);

                    ProceedActivity.this.startActivity(intent);
                }
            }
        });
    }

    private boolean allValid(){
        ArrayList<String> errorMessage = new ArrayList<>();
        boolean valid = true;

        if(lastName.equals("") || firstName.equals("")){
            errorMessage.add("お名前\n");
            valid = false;
        }
        if(zip1.equals("") || zip2.equals("") ){
            errorMessage.add("郵便番号\n");
            valid = false;
        }
        if(prefecture.equals("")){
            errorMessage.add("都道府県\n");
            valid = false;
        }
        if(address1.equals("") && address2.equals("")){
            errorMessage.add("ご住所\n");
            valid = false;
        }
        if(apartment.equals("")){
            apartment = "---";
        }
        if(email.equals("")){
            errorMessage.add("メールアドレス\n");
            valid = false;
        }
        if(phone.equals("")){
            errorMessage.add("電話番号");
            valid = false;
        }
        if(company.equals("")){
            company = "---";
        }

        if(!valid){
            Toast.makeText(ProceedActivity.this, errorMessage + "が抜けています", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!email.contains("@")){
            Toast.makeText(ProceedActivity.this, "正しいメールアドレスを入力してください", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
