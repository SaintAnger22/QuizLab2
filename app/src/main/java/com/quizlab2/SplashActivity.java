package com.quizlab2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {

    public static ArrayList<Modelclass> list;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity_main);

        list=new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Question");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Modelclass modelclass = dataSnapshot.getValue(Modelclass.class);
                    list.add(modelclass);
                }

                Intent intent = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();

//                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
//                startActivity(intent);
            }

            @Override // Add `void` return type here
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        list.add(new Modelclass("Что является столицей Республики Беларусь?","Минск","Витебск","Могилёв", "Гродно", "Минск"));
//        list.add(new Modelclass("На чём написано данное приложение?","Flutter","Kotlin","Java", "Swift", "Java"));
//        list.add(new Modelclass("Нажмите на 1","3","1","4", "2", "1"));
//        list.add(new Modelclass("Нажмите на A","A","B","C", "D", "A"));
//        list.add(new Modelclass("2+2=?","2","3","5", "4", "4"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
//                startActivity(intent);
            }
        },1500);
    }
}