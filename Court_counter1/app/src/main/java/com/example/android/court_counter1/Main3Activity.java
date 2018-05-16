package com.example.android.court_counter1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    private TextView show_a;
    private TextView show_b;
    private TextView show_final;
    private int team_a;
    private int team_b;
    private Button new_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle extras=getIntent().getExtras();
        show_a=(TextView)findViewById(R.id.part_A);
        show_b=(TextView)findViewById(R.id.part_B);
        show_final=(TextView)findViewById(R.id.part_C);
        if(extras!=null)
        {
            team_a=extras.getInt("ScoreA");
            team_b=extras.getInt("ScoreB");
            show_a.setText(String.valueOf(team_a));
            show_b.setText(String.valueOf(team_b));
            if(team_a>team_b)
            {
                show_final.setText("Team A Wins.");
            }
            else if(team_b>team_a)
            {
                show_final.setText("Team B Wins.");
            }
            else
            {
                show_final.setText("Match is Draw");
            }
        }
        new_game=(Button)findViewById(R.id.new_game);
        new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main3Activity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
