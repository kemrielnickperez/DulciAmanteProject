package com.example.dulci1;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CoffeeSelection extends AppCompatActivity {
    Button btnHotLatte,btnMacchiato,btnUpdate,btnMilktea,btnEspresso,btnCupCake,btnCake,btnIceCream,btnTurkishDelight,btnAddToCart,btnRemove,btnCheckOut, btnPlus, btnDiminish;
    EditText etSize,etSugarLevel,etItem;
    TextView tvCoffeeSelect, tvPrice;
    TextView tvNumItem;
    SharedPreferences sp;
    double priceNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp=getSharedPreferences("My_Prefs", Activity.MODE_PRIVATE);
        setContentView(R.layout.activity_coffee_selection);
        btnHotLatte=findViewById(R.id.btnHotLatte);
        btnMacchiato=findViewById(R.id.btnMacchiato);
        btnMilktea=findViewById(R.id.btnMilkTea);
        btnEspresso=findViewById(R.id.btnEspresso);
        btnCake=findViewById(R.id.btnCake);
        btnCupCake=findViewById(R.id.btnCupCake);
        btnIceCream=findViewById(R.id.btnIceCream);
        btnTurkishDelight=findViewById(R.id.btnTurkishDelight);
        btnAddToCart=findViewById(R.id.btnAddToCart);
        etItem=findViewById(R.id.etItem);
        tvCoffeeSelect=findViewById(R.id.TvCoffeeSelect);
        tvPrice=findViewById(R.id.tvPrice);
        btnRemove=findViewById(R.id.btnRemove);
        btnUpdate=findViewById(R.id.btnUpdate);
        etSize=findViewById(R.id.et_Size);
        etSugarLevel=findViewById(R.id.et_SugarLevel);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor e= sp.edit();
                e.putString("Your Coffee",tvCoffeeSelect.getText().toString());
                e.putString("Size",etSize.getText().toString());
                e.putString("Sugar Level",etSugarLevel.getText().toString());
                e.putString("Item", etItem.getText().toString());
                e.putString("Price", tvPrice.getText().toString());
                e.commit();
                Intent i= new Intent(CoffeeSelection.this,CheckOut.class);
                startActivity(i);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int item1= Integer.parseInt(etItem.getText().toString());
                priceNum*=item1;
                tvPrice.setText(String.valueOf(priceNum));

            }
        });
    }
    public void onClickHotLatte(View view){
        tvCoffeeSelect.setText("Hot Lattee");


        priceNum=200.00;
        tvPrice.setText("200.00");
    }
    public void onClickMacchiato(View view){
        tvCoffeeSelect.setText("Macchiato");


        priceNum=175.20;
        tvPrice.setText("175.20");
    }
    public void onClickMilkTea(View view){
        tvCoffeeSelect.setText("MilkTea");


        priceNum=185.99;
        tvPrice.setText("185.99");
    }
    public void onClickEspresso(View view){
        tvCoffeeSelect.setText("Espresso");

        priceNum=200.50;
        tvPrice.setText("200.50");
    }
    public void onClickCupCake(View view){
        tvCoffeeSelect.setText("CupCake");
        etSize.setEnabled(false);
        etSugarLevel.setEnabled(false);


        priceNum=215.25;
        tvPrice.setText("215.25");
    }
    public void onClickCake(View view){
        tvCoffeeSelect.setText("Cake");
        etSize.setEnabled(false);
        etSugarLevel.setEnabled(false);

        priceNum=450.50;
        tvPrice.setText("450.50");
    }
    public void onClickIceCream(View view){
        tvCoffeeSelect.setText("Ice Cream");
        etSize.setEnabled(false);
        etSugarLevel.setEnabled(false);

        priceNum=350.00;
        tvPrice.setText("350.00");
    }
    public void onClickTurkishDelight(View view){
        tvCoffeeSelect.setText("Turkish Delight");
        etSize.setEnabled(false);
        etSugarLevel.setEnabled(false);

        priceNum=100.00;
        tvPrice.setText("100.00");
    }
    public void onClickRemove(View view){
        tvCoffeeSelect.setText("");
        etSize.setText("");
        etSugarLevel.setText("");
        etItem.setHint("1");
        tvPrice.setText("");
    }

}