package com.example.firebaseapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText text1,text2,text3,text4;
    Button button_save;
    Facility fa;
    DatabaseReference reff;
    public Context c=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reff=FirebaseDatabase.getInstance().getReference().child("facilities");
        text1=findViewById(R.id.text_1);
        text3=findViewById(R.id.text_3);
        fa=new Facility();
        button_save=findViewById(R.id.button);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                LinearLayout ll=findViewById(R.id.mainL);
                LinearLayout nll=new LinearLayout(c);
                nll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(nll);
                for (DataSnapshot ds:dataSnapshot.getChildren()) {
                    Facility facility = ds.getValue(Facility.class);
                    TextView tt=new TextView(c);
                    tt.setText(facility.name);
                    nll.addView(tt);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
//        reff.addChildEventListener(
//                new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                        String a=dataSnapshot.child("name").getValue().toString();
//                        Toast toast=Toast.makeText(c,a,Toast.LENGTH_SHORT);
//                        Log.d("FIRE",a);
//                    }
//
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                }
//        );

        button_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String addreass=text3.getText().toString().trim();
                String location=text1.getText().toString().trim();
                fa.name=addreass;
                fa.type=location;


                reff.push().setValue(fa);
                text1.setText("");
                text3.setText("");
            }
        });
    }
}
