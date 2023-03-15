package com.example.dulci1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ReviewPage extends AppCompatActivity {
    Button btnHotLatte1, btnMacchiato1, btnUpdate0, btnMilktea1, btnEspresso1, btnCupCake1, btnCake1, btnIceCream1, btnTurkishDelight1, btnOk, btnCancel, btnSearch, btnUpdate1;
    EditText etSize1, etSugarLevel1, etItem1, etFirstName1, etContactNumber1, etLastName1, etEmailAddress1, etAddress1, etGender1;
    TextView tvCoffeeSelect1, tvPrice1, tvUrOrder1;
    //TextInputLayout etSearchName,etSearchContact;
    SharedPreferences sp;
    double priceNum1;
    DatabaseReference reference;
    String cs_firstName, cs_lastName, cs_contactNumber, cs_EmailAddress, cs_Address, cs_Order, cs_Size, cs_Item, cs_Price, cs_Gender, cs_SugarLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);
        //etSearchName=findViewById(R.id.etSearchName);
        etFirstName1 = findViewById(R.id.et_FirstName1);
        etContactNumber1 = findViewById(R.id.et_ContactNumber1);
        etLastName1 = findViewById(R.id.et_LastName1);
        etEmailAddress1 = findViewById(R.id.et_EmailAddress1);
        etAddress1 = findViewById(R.id.et_Address1);
        tvUrOrder1 = findViewById(R.id.tvUrOrder1);
        btnHotLatte1 = findViewById(R.id.btnHotLatte1);
        btnMacchiato1 = findViewById(R.id.btnMacchiato1);
        btnMilktea1 = findViewById(R.id.btnMilkTea1);
        btnEspresso1 = findViewById(R.id.btnEspresso1);
        btnCake1 = findViewById(R.id.btnCake1);
        btnCupCake1 = findViewById(R.id.btnCupCake1);
        btnIceCream1 = findViewById(R.id.btnIceCream1);
        btnTurkishDelight1 = findViewById(R.id.btnTurkishDelight1);
        etItem1 = findViewById(R.id.etItem1);
        tvCoffeeSelect1 = findViewById(R.id.TvCoffeeSelect);
        tvPrice1 = findViewById(R.id.tvPrice1);
        btnUpdate0 = findViewById(R.id.btnUpdate0);
        etSize1 = findViewById(R.id.et_Size1);
        etSugarLevel1 = findViewById(R.id.et_SugarLevel1);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate1 = findViewById(R.id.btnUpdate1);
        etGender1 = findViewById(R.id.et_Gender1);


        reference = FirebaseDatabase.getInstance().getReference().child("Customer");
        showAllCustomerData();
        btnUpdate0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int item1 = Integer.parseInt(etItem1.getText().toString());
                priceNum1 *= item1;
                tvPrice1.setText(String.valueOf(priceNum1));
            }
        });
        dbCustomer db = new dbCustomer();
        btnCancel.setOnClickListener(v ->{
            db.remove("" + etFirstName1.getText().toString()).addOnSuccessListener(suc -> {
                Toast.makeText(this, "Your Order has been canceled.", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er -> {
                Toast.makeText(this, "error" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("firstName", etFirstName1.getText().toString());
        hashMap.put("lastName", etLastName1.getText().toString());
        hashMap.put("contactNumber", etContactNumber1.getText().toString());
        hashMap.put("email", etEmailAddress1.getText().toString());
        hashMap.put("address", etAddress1.getText().toString());
        hashMap.put("gender", etGender1.getText().toString());
        hashMap.put("order", tvUrOrder1.getText().toString());
        hashMap.put("size", etSize1.getText().toString());
        hashMap.put("item", etItem1.getText().toString());
        hashMap.put("sugarLevel", etSugarLevel1.getText().toString());
        hashMap.put("price", tvPrice1.getText().toString());


    }

    private void showAllCustomerData() {
        Intent intent = getIntent();
        cs_firstName = intent.getStringExtra("firstName");
        cs_lastName = intent.getStringExtra("lastName");
        cs_contactNumber = intent.getStringExtra("contactNumber");
        cs_EmailAddress = intent.getStringExtra("email");
        cs_Address = intent.getStringExtra("address");
        cs_Order = intent.getStringExtra("order");
        cs_Size = intent.getStringExtra("size");
        cs_Item = intent.getStringExtra("item");
        cs_Price = intent.getStringExtra("price");
        cs_Gender = intent.getStringExtra("gender");
        cs_SugarLevel = intent.getStringExtra("sugarLevel");

        etFirstName1.setText(cs_firstName);
        etLastName1.setText(cs_lastName);
        etContactNumber1.setText(cs_contactNumber);
        etEmailAddress1.setText(cs_EmailAddress);
        etAddress1.setText(cs_Address);
        etGender1.setText(cs_Gender);
        tvUrOrder1.setText(cs_Order);
        etSize1.setText(cs_Size);
        etSugarLevel1.setText(cs_SugarLevel);
        etItem1.setText(cs_Item);
        tvPrice1.setText(cs_Price);

    }


    public void onClickHotLatte1(View view) {
        tvUrOrder1.setText("Hot Lattee");
        etSize1.setEnabled(true);
        etSugarLevel1.setEnabled(true);

        priceNum1 = 200.00;
        tvPrice1.setText("200.00");
    }

    public void onClickMacchiato1(View view) {
        tvUrOrder1.setText("Macchiato");
        etSize1.setEnabled(true);
        etSugarLevel1.setEnabled(true);

        priceNum1 = 175.20;
        tvPrice1.setText("175.20");
    }

    public void onClickMilkTea1(View view) {
        tvUrOrder1.setText("MilkTea");
        etSize1.setEnabled(true);
        etSugarLevel1.setEnabled(true);

        priceNum1 = 185.99;
        tvPrice1.setText("185.99");
    }

    public void onClickEspresso1(View view) {
        tvUrOrder1.setText("Espresso");
        etSize1.setEnabled(true);
        ;
        priceNum1 = 200.50;
        tvPrice1.setText("200.50");
    }

    public void onClickCupCake1(View view) {
        tvUrOrder1.setText("CupCake");
        etSize1.setEnabled(false);
        etSugarLevel1.setEnabled(false);


        priceNum1 = 215.25;
        tvPrice1.setText("215.25");
    }

    public void onClickCake1(View view) {
        tvUrOrder1.setText("Cake");
        etSize1.setEnabled(false);
        etSugarLevel1.setEnabled(false);

        priceNum1 = 450.50;
        tvPrice1.setText("450.50");
    }

    public void onClickIceCream1(View view) {
        tvUrOrder1.setText("Ice Cream");
        etSize1.setEnabled(false);
        etSugarLevel1.setEnabled(false);

        priceNum1 = 350.00;
        tvPrice1.setText("350.00");
    }

    public void onClickTurkishDelight1(View view) {
        tvUrOrder1.setText("Turkish Delight");
        etSize1.setEnabled(false);
        etSugarLevel1.setEnabled(false);

        priceNum1 = 100.00;
        tvPrice1.setText("100.00");
    }

    public void onClickUpdate(View view) {
        if (firstNameUpdate() || contactNumberUpdate() || AddressUpdate() || emailUpdate() || GenderUpdate() || LastNameUpdate() || OrderUpdate() || PriceUpdate() || SizeUpdate() || SugarLevelUpdate() || ItemUpdate()) {
            Toast.makeText(this, "Order has been Updated", Toast.LENGTH_LONG).show();
        }

    }

    private boolean ItemUpdate() {
        if (!cs_Item.equals(etItem1.getText().toString())) {
            reference.child(cs_firstName).child("item").setValue(etItem1.getText().toString());
            cs_Item = etItem1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean firstNameUpdate() {
        if (!cs_firstName.equals(etFirstName1.getText().toString())) {
            reference.child(cs_firstName).child("firstName").setValue(etFirstName1.getText().toString());
            cs_firstName = etFirstName1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean AddressUpdate() {
        if (!cs_Address.equals(etAddress1.getText().toString())) {
            reference.child(cs_firstName).child("address").setValue(etAddress1.getText().toString());
            cs_Address = etAddress1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean emailUpdate() {
        if (!cs_EmailAddress.equals(etEmailAddress1.getText().toString())) {
            reference.child(cs_firstName).child("email").setValue(etEmailAddress1.getText().toString());
            cs_EmailAddress = etEmailAddress1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean GenderUpdate() {
        if (!cs_Gender.equals(etGender1.getText().toString())) {
            reference.child(cs_firstName).child("gender").setValue(etGender1.getText().toString());
            cs_Gender = etGender1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean LastNameUpdate() {
        if (!cs_lastName.equals(etLastName1.getText().toString())) {
            reference.child(cs_firstName).child("lastName").setValue(etLastName1.getText().toString());
            cs_lastName = etLastName1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean OrderUpdate() {
        if (!cs_Order.equals(tvUrOrder1.getText().toString())) {
            reference.child(cs_firstName).child("order").setValue(tvUrOrder1.getText().toString());
            cs_Order = tvUrOrder1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean SizeUpdate() {
        if (!cs_Size.equals(etSize1.getText().toString())) {
            reference.child(cs_firstName).child("size").setValue(etSize1.getText().toString());
            cs_Size = etSize1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean PriceUpdate() {
        if (!cs_Price.equals(tvPrice1.getText().toString())) {
            reference.child(cs_firstName).child("price").setValue(tvPrice1.getText().toString());
            cs_Price = tvPrice1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean SugarLevelUpdate() {
        if (!cs_SugarLevel.equals(etSugarLevel1.getText().toString())) {
            reference.child(cs_firstName).child("sugarLevel").setValue(etSugarLevel1.getText().toString());
            cs_SugarLevel = etSugarLevel1.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    private boolean contactNumberUpdate() {
        if (!cs_contactNumber.equals(etContactNumber1.getText().toString())) {
            reference.child(cs_firstName).child("contactNumber").setValue(etContactNumber1.getText().toString());
            cs_contactNumber = etContactNumber1.getText().toString();

            return true;
        } else {
            return false;
        }
    }

    /*
        private boolean ItemCancel() {
            if(cs_Item.equals(etItem1.getText().toString())){
                reference.child("item").child("item").removeValue();
                return true;
            }
            else{
                return false;
            }
        }
        private boolean firstNameCancel() {
            if(cs_firstName.equals(etFirstName1.getText().toString())){
                reference.child(cs_firstName).child("firstName").removeValue();
                return true;
            }
            else{
                return false;
            }
        }
        private boolean AddressCancel() {
            if(cs_Address.equals(etAddress1.getText().toString())){
                reference.child(cs_firstName).child("address").removeValue();
                return true;
            }
            else{
                return false;
            }
        }
        private boolean emailCancel() {
            if(cs_EmailAddress.equals(etEmailAddress1.getText().toString())){
                reference.child(cs_firstName).child("email").removeValue();
                return true;
            }
            else{
                return false;
            }
        }
        private boolean GenderCancel() {
                reference.child(cs_firstName).child("gender").setValue(null);
                return true;

        }
        private boolean LastNameCancel() {
            if(cs_lastName.equals(etLastName1.getText().toString())){
                reference.child(cs_firstName).child("lastName").setValue(null);
                return true;
            }
            else{
                return false;
            }
        }
        private boolean OrderCancel() {
            if(cs_Order.equals(tvUrOrder1.getText().toString())){
                reference.child(cs_firstName).child("order").setValue(null);
                return true;
            }
            else{
                return false;
            }
        }
        private boolean SizeCancel() {
            if(cs_Size.equals(etSize1.getText().toString())){
                reference.child(cs_firstName).child("size").setValue(null);
                return true;
            }
            else{
                return false;
            }
        }
        private boolean PriceCancel() {
            if(cs_Price.equals(tvPrice1.getText().toString())){
                reference.child(cs_firstName).child("price").setValue(null);
                return true;
            }
            else{
                return false;
            }
        }
        private boolean SugarLevelCancel() {
            if(cs_SugarLevel.equals(etSugarLevel1.getText().toString())){
                reference.child(cs_firstName).child("sugarLevel").setValue(null);
                return true;
            }
            else{
                return false;
            }
        }
        private boolean contactNumberCancel() {
            if(cs_contactNumber.equals(etContactNumber1.getText().toString())){
                reference.child(cs_firstName).child("contactNumber").setValue(null);


                return true;
            }
            else{
                return false;
            }
        }

        public void onClickCancel(View view){
            reference.child(cs_firstName).removeValue();
            Toast.makeText(getApplicationContext(),"Order has been Cancelled",Toast.LENGTH_LONG).show();
        }
*/
    public void onClickOk(View view) {
        Intent i= new Intent(ReviewPage.this,Login.class);
        startActivity(i);
    }
}


