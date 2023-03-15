package com.example.dulci1;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Details extends AppCompatActivity {
    EditText et_Firstname, et_Lastname, et_Email, et_ContactNumber, et_Gender,et_StNumber, et_PostalCode, et_City;
    SharedPreferences sp;
    Button btnProceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        sp=getSharedPreferences("My_Prefs", Activity.MODE_PRIVATE);
        et_Firstname=findViewById(R.id.et_FirstName);
        et_Lastname=findViewById(R.id.et_LastName);
        et_Email=findViewById(R.id.et_Email);
        et_ContactNumber=findViewById(R.id.et_ContactNumber);
        et_StNumber=findViewById(R.id.et_StNumber);
        et_PostalCode=findViewById(R.id.et_PostalCode);
        et_Gender=findViewById(R.id.et_Gender);
        et_City=findViewById(R.id.et_City);
        btnProceed=findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor e= sp.edit();
                e.putString("First Name",et_Firstname.getText().toString());
                e.putString("Last Name",et_Lastname.getText().toString());
                e.putString("Email",et_Email.getText().toString());
                e.putString("Contact Number",et_ContactNumber.getText().toString());
                e.putString("Gender",et_Gender.getText().toString());
                e.putString("Street Number",et_StNumber.getText().toString());
                e.putString("Postal Code",et_PostalCode.getText().toString());
                e.putString("City",et_City.getText().toString());
                e.commit();
                Intent i= new Intent(Details.this,CoffeeSelection.class);
                startActivity(i);
            }
        });
    }

}