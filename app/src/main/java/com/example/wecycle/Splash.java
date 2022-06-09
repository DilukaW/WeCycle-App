package com.example.wecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash extends AppCompatActivity {

    LottieAnimationView lottieAnimationView;
    ImageView txtHeading;
    TextView copyRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtHeading=findViewById(R.id.heading);
        copyRight=findViewById(R.id.txtCopyRight);
        lottieAnimationView=findViewById(R.id.lottieAnimationView);

        lottieAnimationView.animate().translationX(2000).setDuration(2000).setStartDelay(2000);
        txtHeading.animate().translationX(2000).setDuration(2000).setStartDelay(1800);
        copyRight.animate().translationX(2000).setDuration(2000).setStartDelay(1800);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this,MainActivity.class));
                finish();
            }
        },3000);

    }
}