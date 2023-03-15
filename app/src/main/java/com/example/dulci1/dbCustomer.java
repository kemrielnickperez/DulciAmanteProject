package com.example.dulci1;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class dbCustomer {
    private DatabaseReference databaseReference;
    public dbCustomer(){
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference=db.getReference(Customer.class.getSimpleName());
    }
    public Task<Void> add(Customer customer){
        //push node
       return databaseReference.child(customer.getFirstName()).setValue(customer);
    }
    public Task<Void> remove(String customer){
        return databaseReference.child(customer).removeValue();
    }
}
