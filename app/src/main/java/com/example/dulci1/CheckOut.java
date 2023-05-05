package com.example.dulci1;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CheckOut extends AppCompatActivity {
   // TextView tvlName, tvEmail,tvContactNumber,tvAddress, tvTotal,tvOrder,tvOrder1,tvOrder2,tvOrder3,tvGender;
    Button btnOrder, btnNext;
    SharedPreferences sp;
    DatabaseReference reference;
    FirebaseDatabase rNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        sp = getSharedPreferences("My_Prefs", Activity.MODE_PRIVATE);
        final TextView tvfName = findViewById(R.id.tvfName);
        final TextView tvlName = findViewById(R.id.tvlName);
        final TextView tvEmail = findViewById(R.id.tvEmail);
        final TextView tvGender = findViewById(R.id.tvGender);
        final TextView tvContactNumber = findViewById(R.id.tvContactNumber);
        final TextView tvAddress = findViewById(R.id.tvAddress);
        final TextView tvTotal = findViewById(R.id.tvTotal);
        final TextView tvOrder = findViewById(R.id.tvOrder);
        final TextView tvOrder1 = findViewById(R.id.tvOrder1);
        final TextView tvOrder2 = findViewById(R.id.tvOrder2);
        final TextView tvOrder3 = findViewById(R.id.tvOrder3);
        btnOrder = findViewById(R.id.btnOrder);
        btnNext=findViewById(R.id.btnROrder);
        sp = getSharedPreferences("My_Prefs", Activity.MODE_PRIVATE);
        tvfName.setText(sp.getString("First Name", "N/A"));
        tvlName.setText(sp.getString("Last Name", "N/A"));
        tvEmail.setText(sp.getString("Email", "N/A"));
        tvContactNumber.setText(sp.getString("Contact Number", "N/A"));
        tvGender.setText(sp.getString("Gender", "N/A"));
        tvAddress.setText(sp.getString("Street Number", "N/A") + ", " + sp.getString("City", "N/A") + ", " + sp.getString("Postal Code", "N/A"));
        tvOrder.setText(sp.getString("Your Coffee", " "));
        tvOrder1.setText("" + sp.getString("Size", ""));
        tvOrder2.setText("" + sp.getString("Sugar Level", ""));
        tvOrder3.setText("" + sp.getString("Item", ""));
        tvTotal.setText(sp.getString("Price", ""));
        dbCustomer db = new dbCustomer();
        btnOrder.setOnClickListener(v ->
        {
            Customer customer = new Customer(tvfName.getText().toString(), tvlName.getText().toString(), tvEmail.getText().toString(), tvContactNumber.getText().toString(), tvGender.getText().toString(), tvAddress.getText().toString(), tvOrder.getText().toString(), tvOrder1.getText().toString(), tvOrder2.getText().toString(), tvOrder3.getText().toString(), tvTotal.getText().toString());
            db.add(customer).addOnSuccessListener(suc->{
                Toast.makeText(this, "Your Order will be coming soon", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
    public void onClickReview(View view) {
        Intent i= new Intent(CheckOut.this,Login.class);
        startActivity(i);}
}