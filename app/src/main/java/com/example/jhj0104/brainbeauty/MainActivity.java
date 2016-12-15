package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.animation.AnimationUtils.loadAnimation;

public class MainActivity extends AppCompatActivity {
    private long lastTimeBackPressed;
    Animation animBlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_main);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences pref =getSharedPreferences("isSecond", MODE_PRIVATE);
                Boolean Istest = false;

                if(Istest == false && pref.getBoolean("isSecond",false)){
                    Intent myIntent = new Intent(MainActivity.this, BB_menu.class);
                    MainActivity.this.startActivity(myIntent);
                }
                else{
                    Intent myIntent = new Intent(MainActivity.this, PersonalData.class);
                    MainActivity.this.startActivity(myIntent);
                }
                finish();
            }
        });


        TextView tv = (TextView) findViewById(R.id.pressToStart);
        //animBlink = AnimationUtils.loadAnimation(this, R.anim.);

        android.view.animation.Animation blinkAnimation =  loadAnimation(getApplicationContext(), R.anim.blink_animation);
        tv.startAnimation(blinkAnimation);
    }

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        Toast.makeText(getBaseContext(), "'뒤로'버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();

    }
}
