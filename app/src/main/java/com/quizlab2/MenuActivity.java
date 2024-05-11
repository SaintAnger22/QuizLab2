package com.quizlab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularprogressbar.BuildConfig;

public class MenuActivity extends AppCompatActivity {

    Button btnExit, btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnExit = findViewById(R.id.btnExit);
//        btnResult = findViewById(R.id.btnResult);
        btnStart = findViewById(R.id.btnStart);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent btnStart = new Intent(MenuActivity.this, DashboardActivity.class);
                    startActivity(btnStart);
                    finish();
                } catch (Exception e){

                }

            }
        });

//        btnResult.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    Intent btnResult = new Intent(MenuActivity.this, ResultActivity.class);
//                    startActivity(btnResult);
//                    finish();
//                } catch (Exception e){
//
//                }
//
//            }
//        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    finish();
                } catch (Exception e){

                }

            }
        });
    }
}
