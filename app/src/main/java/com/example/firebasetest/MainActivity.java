package com.example.firebasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    TextView airtest,soundtest;


    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference user = myRef.child("users");
    DatabaseReference firebase_data_air =user.child("niluksha").child("AirTest");
    DatabaseReference firebase_data_sound =user.child("niluksha").child("Sound");
    ProgressBar progressBar1,progressBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airtest = findViewById(R.id.text1);
        soundtest = findViewById(R.id.text3);
        progressBar1 = (ProgressBar) findViewById(R.id.progressbarsound);
        progressBar2 = (ProgressBar) findViewById(R.id.progressbarair);

        getdata();
    }
    public void getdata(){
        // Read from the database
        firebase_data_air.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Float value = dataSnapshot.getValue(Float.class);
                airtest.setText(value.toString());
                progressBar2.setSecondaryProgress(Math.round(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });


        // Read Sound Value from the database
        firebase_data_sound.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Float value = dataSnapshot.getValue(Float.class);
                soundtest.setText(value.toString()+" DB");
                progressBar1.setSecondaryProgress(Math.round(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });
    }
    public void onclickanalatics(View view){
        Intent mintent = new Intent(this,analatics.class);
        startActivity(mintent);
    }
}
