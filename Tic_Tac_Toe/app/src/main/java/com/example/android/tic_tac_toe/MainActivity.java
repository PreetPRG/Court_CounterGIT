package com.example.android.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 is for x,1 is for 0
    boolean game_is_active=true;
    String winner;
    int cross=0;
    int zero=1;
    int flag=cross;
    int check;
    int[] slot={2,2,2,2,2,2,2,2,2};//Initialized to 2 as every block is empty
    int[][] state={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view)
    {
        ImageView counter=(ImageView)view;
        counter.setTranslationY(-1000f);
        check=Integer.parseInt(counter.getTag().toString());
        if(flag==cross && game_is_active){
            if(slot[check]==2){
                slot[check]=cross;
                counter.setImageResource(R.drawable.x_tic);
                flag=zero;
            }
            else{
                Toast.makeText(MainActivity.this, "Place Your finger at right place",
                        Toast.LENGTH_LONG).show();
            }
        }
        else if(flag==zero && game_is_active){
            if(slot[check]==2){
                slot[check]=zero;
                counter.setImageResource(R.drawable.o_tac);
                flag=cross;
            }
            else{
                Toast.makeText(MainActivity.this, "Place Your finger at right place",
                        Toast.LENGTH_LONG).show();
            }
        }
        counter.animate().translationYBy(1000f).setDuration(500);
        for(int[] states:state)
        {
            if(slot[states[0]]==slot[states[1]] && slot[states[1]]==slot[states[2]] && slot[states[0]]!=2) {
                game_is_active=false;
                if ( slot[states[0]]== cross) {
                    winner="cross";
                }
                else {
                    winner="Oval";
                }
                TextView winners=(TextView)findViewById(R.id.winner_message);
                winners.setText(winner + " has Won!!!");
                LinearLayout linearlayout=(LinearLayout)findViewById(R.id.play_again);
                linearlayout.setVisibility(View.VISIBLE);
            }
            else{
                boolean game_is_over=true;
                for(int i=0;i<slot.length;i++){
                    if(slot[i]==2)
                    {
                        game_is_over=false;
                    }

                }
                if(game_is_over)
                {
                    game_is_active=false;
                    TextView winners=(TextView)findViewById(R.id.winner_message);
                    winners.setText("Match is Draw!!!");
                    LinearLayout linearlayout=(LinearLayout)findViewById(R.id.play_again);
                    linearlayout.setVisibility(View.VISIBLE);
                }
            }
        }

    }
    public void play_Again(View view)
    {
        game_is_active=true;
        flag=0;
        LinearLayout linearlayout=(LinearLayout)findViewById(R.id.play_again);
        linearlayout.setVisibility(View.INVISIBLE);
        for(int i=0;i< slot.length;i++)
        {
            slot[i]=2;
        }
        GridLayout gridlayout=(GridLayout)findViewById(R.id.GridLayout);
        for(int i=0;i<gridlayout.getChildCount();i++){
            ((ImageView)gridlayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     }
}
