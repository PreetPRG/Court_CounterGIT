package com.example.android.court_counter1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private Button finalend;
    int total_A=0;
    int total_B=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        finalend=(Button)findViewById(R.id.final_results);




    }
    public void increment_a_by_three(View view)
    {
        total_A=total_A + 3;
        display();
    }
    public void increment_a_by_two(View view)
    {
        total_A=total_A + 2;
        display();
    }
    public void increment_a_by_one(View view)
    {
        total_A=total_A + 1;
        display();
    }

    public void increment_b_by_three(View view)
    {
        total_B=total_B + 3;
        display();
    }
    public void increment_b_by_two(View view)
    {
        total_B=total_B + 2;
        display();
    }
    public void increment_b_by_one(View view)
    {
        total_B=total_B + 1;
        display();
    }
    public void display()
    {
        TextView Total_A=(TextView)findViewById(R.id.team_a_score);
        Total_A.setText("" + total_A);
        TextView Total_B=(TextView)findViewById(R.id.team_b_score);
        Total_B.setText("" + total_B);
    }
    public void end_game(View view)
    {
        Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
        intent.putExtra("ScoreA",total_A);
        intent.putExtra("ScoreB",total_B);
        startActivity(intent);
    }

}
