package com.example.dulci1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    TextInputLayout etSearchName1,etSearchContact1;
    Button btnContinue1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etSearchName1=findViewById(R.id.SearchName1);
        etSearchContact1=findViewById(R.id.SearchContact1);
        btnContinue1=findViewById(R.id.btnContinue1);
    }

    private Boolean isValidName(){
        String valid= etSearchName1.getEditText().getText().toString();
        String noWhiteSpace="\\A\\w{4,20}\\z";
        if(valid.isEmpty()){
            etSearchName1.setError("It cannot be Empty");
            return false;
        }
        else if(!valid.matches(noWhiteSpace)){
            etSearchName1.setError("Spaces are not Allowed");
            return false;
        }

        else{
            etSearchName1.setError(null);
            etSearchName1.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean isValidContact(){
        String valid= etSearchContact1.getEditText().getText().toString();
        String noWhiteSpace="\\A\\w{4,20}\\z";
        if(valid.isEmpty()){
            etSearchContact1.setError("It cannot be Empty");
            return false;
        }
        else if(!valid.matches(noWhiteSpace)){
            etSearchContact1.setError("Spaces are not Allowed");
            return false;
        }

        else{
            etSearchContact1.setError(null);
            etSearchContact1.setErrorEnabled(false);
            return true;
        }
    }
    public void isValidCustomer(View view){
        /*if(!isValidName() | !isValidContact()){
           return;
        }*/
        etSearchContact1.getEditText().getText().toString();
        etSearchName1.getEditText().getText().toString();
        isCustomer();
    }
    private void isCustomer() {
        String nameValidate=etSearchName1.getEditText().getText().toString().trim();
        String contactValidate=etSearchContact1.getEditText().getText().toString().trim();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Customer");

        Query checkName= ref.orderByChild("firstName").equalTo(nameValidate);
        // Query checkUser= ref.orderByChild("contactNumber").equalTo(contactValidate);
        checkName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    etSearchName1.setError(null);
                    etSearchName1.setErrorEnabled(false);
                    String contactFromDB = snapshot.child(nameValidate).child("contactNumber").getValue(String.class);
                    if(contactFromDB.equals(contactValidate)){
                        etSearchName1.setError(null);
                        etSearchName1.setErrorEnabled(false);
                        String addressFromDB=snapshot.child(nameValidate).child("address").getValue(String.class);
                        String emailFromDB=snapshot.child(nameValidate).child("email").getValue(String.class);
                        String firstNameFromDB=snapshot.child(nameValidate).child("firstName").getValue(String.class);
                        String genderFromDB=snapshot.child(nameValidate).child("gender").getValue(String.class);
                        String itemFromDB=snapshot.child(nameValidate).child("item").getValue(String.class);
                        String lastNameFromDB=snapshot.child(nameValidate).child("lastName").getValue(String.class);
                        String orderFromDB=snapshot.child(nameValidate).child("order").getValue(String.class);
                        String priceFromDB=snapshot.child(nameValidate).child("price").getValue(String.class);
                        String sizeFromDB=snapshot.child(nameValidate).child("size").getValue(String.class);
                        String sugarLevelFromDB=snapshot.child(nameValidate).child("sugarLevel").getValue(String.class);
                        String contactNumberFromDB=snapshot.child(nameValidate).child("contactNumber").getValue(String.class);

                        Intent intent=new Intent(getApplicationContext(),ReviewPage.class);
                        intent.putExtra("lastName",lastNameFromDB);
                        intent.putExtra("address",addressFromDB);
                        intent.putExtra("contactNumber",contactNumberFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("firstName",firstNameFromDB);
                        intent.putExtra("gender",genderFromDB);
                        intent.putExtra("item",itemFromDB);
                        intent.putExtra("order",orderFromDB);
                        intent.putExtra("price",priceFromDB);
                        intent.putExtra("size",sizeFromDB);
                        intent.putExtra("sugarLevel",sugarLevelFromDB);

                        startActivity(intent);

                    }
                    else{
                        etSearchContact1.setError("Your Contact Number is incorrect");
                        etSearchName1.requestFocus();
                    }
                }
                else{
                    etSearchName1.setError("First Name doesn't exist.");
                    etSearchName1.requestFocus();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}