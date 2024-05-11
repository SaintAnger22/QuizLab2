package com.quizlab2;

import static com.quizlab2.SplashActivity.list;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    int timerValue=20;
    ProgressBar progressBar;
    List<Modelclass> allQuestionsList;
    Modelclass modelclass;
    int index=0;
    TextView card_question, optionA, optionB, optionC, optionD;
    CardView cardOA, cardOB, cardOC, cardOD;
    int correctCount = 0;
    int wrongCount = 0;
    LinearLayout nextBtn;

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Hooks();

        allQuestionsList=list;
        Collections.shuffle(allQuestionsList);
        modelclass=list.get(index);

        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));

        nextBtn.setClickable(false);

        countDownTimer=new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue=timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                Dialog dialog=new Dialog(DashboardActivity.this, R.style.Dialog);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);

                dialog.findViewById(R.id.btn_tryAgain).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(DashboardActivity.this, SplashActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        }.start();

        setAllData();
    }

    private void setAllData() {
        card_question.setText(modelclass.getQuestion());
        optionA.setText(modelclass.getoA());
        optionB.setText(modelclass.getoB());
        optionC.setText(modelclass.getoC());
        optionD.setText(modelclass.getoD());
        timerValue=20;
        countDownTimer.cancel();
        countDownTimer.start();
        enableButton();
    }

    private void Hooks() {
        progressBar=findViewById(R.id.quiz_timer);
        card_question=findViewById(R.id.card_question);
        optionA=findViewById(R.id.card_option_a);
        optionB=findViewById(R.id.card_option_b);
        optionC=findViewById(R.id.card_option_c);
        optionD=findViewById(R.id.card_option_d);

        cardOA=findViewById(R.id.cardA);
        cardOB=findViewById(R.id.cardB);
        cardOC=findViewById(R.id.cardC);
        cardOD=findViewById(R.id.cardD);

        nextBtn=findViewById(R.id.nextBtn);
        btnBack=findViewById(R.id.btnBack);
    }

    public void Correct(CardView cardView){
        cardView.setBackgroundColor(getResources().getColor(R.color.green));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctCount++;
                if(index < list.size() - 1){
                    index++;
                    modelclass=list.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                } else {
                    GameWon();
                }
            }
        });
    }




    public void Wrong(CardView cardOA){
        cardOA.setBackgroundColor(getResources().getColor(R.color.red));
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongCount++;
                if(index < list.size() - 1){
                    index++;
                    modelclass=list.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                } else {
                    GameWon();
                }
            }
        });
    }

    private void GameWon(){
            Intent intent = new Intent(DashboardActivity.this, WonActivity.class);
            intent.putExtra("correct", correctCount);
            intent.putExtra("wrong", wrongCount);
            startActivity(intent);
            finish();
    }

    public void enableButton(){
        cardOA.setClickable(true);
        cardOB.setClickable(true);
        cardOC.setClickable(true);
        cardOD.setClickable(true);
    }
    public void disableButton(){
        cardOA.setClickable(false);
        cardOB.setClickable(false);
        cardOC.setClickable(false);
        cardOD.setClickable(false);
    }

    public void resetColor(){
        cardOA.setBackgroundColor(getResources().getColor(R.color.white));
        cardOB.setBackgroundColor(getResources().getColor(R.color.white));
        cardOC.setBackgroundColor(getResources().getColor(R.color.white));
        cardOD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionClickA(View view){
        disableButton();
        nextBtn.setClickable(true);
        if (optionA.getText().toString().equals(modelclass.getAns())) {
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.green));
            Correct(cardOA);
        } else {
            cardOA.setCardBackgroundColor(getResources().getColor(R.color.red));
            Wrong(cardOA);
        }
    }


    public void OptionClickB(View view){
        disableButton();
        nextBtn.setClickable(true);
        if (optionB.getText().toString().equals(modelclass.getAns())) {
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.green));
            Correct(cardOB);
        } else {
            cardOB.setCardBackgroundColor(getResources().getColor(R.color.red));
            Wrong(cardOB);
        }
    }

    public void OptionClickC(View view){
        disableButton();
        nextBtn.setClickable(true);
        if (optionC.getText().toString().equals(modelclass.getAns())) {
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.green));
            Correct(cardOC);
        } else {
            cardOC.setCardBackgroundColor(getResources().getColor(R.color.red));
            Wrong(cardOC);
        }
    }

    public void OptionClickD(View view){
        disableButton();
        nextBtn.setClickable(true);
        if (optionD.getText().toString().equals(modelclass.getAns())) {
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.green));
            Correct(cardOD);
        } else {
            cardOD.setCardBackgroundColor(getResources().getColor(R.color.red));
            Wrong(cardOD);
        }
    }

    public void onClickEnd(View view){
        Intent backBtn = new Intent(DashboardActivity.this, MenuActivity.class);
        startActivity(backBtn);
        finish();
    }
}