package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;

    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score=0;
    int numberOfQuestions=0;
    TextView resultTextview;
    TextView pointsTextView;
    TextView sumTextView;
    TextView timerTextView;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    public  void  intiliazer (){
        sumTextView =(TextView) findViewById(R.id.sumTextView);
        resultTextview = (TextView) findViewById(R.id.resultTextView);
        pointsTextView= (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);

        button0 =(Button) findViewById(R.id.button0);
        button1 =(Button) findViewById(R.id.button1);
        button2 =(Button) findViewById(R.id.button2);
        button3 =(Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);

    }

    public  void genereteQuestions()
    {
        Random rand =new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);


        sumTextView.setText(Integer.toString((a)) + "+" + Integer.toString(b));
        locationOfCorrectAnswer =rand.nextInt(4);
        int incorrectAnswer;
        answers.clear();
        for (int i=0 ;i<4;i++)
        {
            if(i==locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else {
                incorrectAnswer=rand.nextInt(41);
                while (incorrectAnswer==a+b)
                {
                    incorrectAnswer=rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public  void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
    }

    public   void  chooseAnswer(View view){
        //Log.i("Tag",view.getTag());
        if(view.getTag().toString().equals((Integer.toString(locationOfCorrectAnswer)))){
            //what to do @correct answer
            score++;
            resultTextview.setText("Correct");
        }
        else{
            resultTextview.setText("wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        genereteQuestions();



    }

    public  void myCountDown(int seconds){
        new CountDownTimer(seconds*1000+100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+ " s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0 s");
                resultTextview.setText("Done");
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();

    }

     public void PlayAgain(View view){
        score =0;
        numberOfQuestions=0;
        timerTextView.setText("30 s");
        pointsTextView.setText("0/0");
        resultTextview.setText("");
        myCountDown(30);
        genereteQuestions();
        playAgainButton.setVisibility(View.INVISIBLE);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        intiliazer();
        PlayAgain(findViewById(R.id.playAgainButton));


    }
}
