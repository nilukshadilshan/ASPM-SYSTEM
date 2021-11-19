package com.example.firebasetest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference user = myRef.child("users");
    DatabaseReference firebase_data_air;

    TextView email,password;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        context = getApplicationContext();

        email.setText("niluksha");
        password.setText("12345");
    }
    public String pass;

    public void onClick(View view){
        String eml = email.getText().toString();
        String pss= password.getText().toString();
        firebase_data_air =user.child(eml).child("Password");
        Intent mintent = new Intent(this,MainActivity.class);
        getdata();
//        if(pss.equalsIgnoreCase(pass)){
            startActivity(mintent);
//        }else{
//            Toast toast = Toast.makeText(context, "Password or Email is wrong", Toast.LENGTH_SHORT);
//            toast.show();
//        }
    }
    public void getdata(){
        // Read from the database
        firebase_data_air.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                pass = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

    }
}
