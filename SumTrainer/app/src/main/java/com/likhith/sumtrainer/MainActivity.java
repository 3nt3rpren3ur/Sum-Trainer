package com.likhith.sumtrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout game;
    Button start,b1,b2,b3,b4,stop;
    TextView question,decision,scoretext,time,final1;
    ArrayList<Integer> answers;
    int c,score=0,tq=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        game=(RelativeLayout)findViewById(R.id.game);
        start=(Button)findViewById(R.id.start);
        stop=(Button)findViewById(R.id.stop);
        question=(TextView)findViewById(R.id.question);
        scoretext=(TextView)findViewById(R.id.scoretext);
        final1=(TextView)findViewById(R.id.final1);
        time=(TextView)findViewById(R.id.time);
        decision=findViewById(R.id.decision);
        answers=new ArrayList<Integer>();
        b1=findViewById(R.id.b0);
        b2=findViewById(R.id.b1);
        b3=findViewById(R.id.b2);
        b4=findViewById(R.id.b3);

    }

    public void startquestions()
    {
        int a = new Random().nextInt(200);
        int b = new Random().nextInt(200);
        question.setText(String.valueOf(a) + "+" + String.valueOf(b));
        c=new Random().nextInt(4);
        int d;
        for(int i=0;i<4;i++)
        {
            if(i==c)
            {
                answers.add(a+b);
            }
            else
            {
                d=new Random().nextInt(400);
                while(d==c)
                {
                    d=new Random().nextInt(400);
                }
                answers.add(d);
            }
        }
        b1.setText(String.valueOf(answers.get(0)));
        b2.setText(String.valueOf(answers.get(1)));
        b3.setText(String.valueOf(answers.get(2)));
        b4.setText(String.valueOf(answers.get(3)));
    }
    public void start(View view)
    {
        start.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);
        stop.setVisibility(View.VISIBLE);
        startquestions();
        new CountDownTimer(600100,1000 )
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                int min=(int) ((millisUntilFinished/1000)/60);
                int sec=(int) ((millisUntilFinished/1000)-(min*60));
                DecimalFormat format = new DecimalFormat("00");
                time.setText(format.format(Double.valueOf(min))+":"+format.format(Double.valueOf(sec)));
            }
            @Override
            public void onFinish()
            {
                game.setVisibility(View.INVISIBLE);
                final1.setText(String.valueOf(score)+"/"+String.valueOf(tq));
                final1.setVisibility(View.VISIBLE);
                stop.setText("Restart");
            }
        }.start();
    }



    public void onClick(View view)
    {
        if(view.getTag().toString().equals(String.valueOf(c)))
        {
            score++;
            decision.setText("you're a genius");
        }
        else
        {
            decision.setText("Better luck next time");
        }
        tq++;
        scoretext.setText(String.valueOf(score)+"/"+String.valueOf(tq));
        answers.clear();
        startquestions();
    }

    public void restart(View view)
    {
        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
