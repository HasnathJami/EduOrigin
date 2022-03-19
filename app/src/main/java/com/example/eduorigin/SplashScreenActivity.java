package com.example.eduorigin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eduorigin.registration.SignInActivity;
import com.example.eduorigin.registration.SignUpActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ImageView img1;
        TextView tv1,tv2;
        Animation top,bottom;
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        img1=findViewById(R.id.mainLogoId);
        tv1=findViewById(R.id.subTextId);
        tv2=findViewById(R.id.subText2Id);
        top= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.mainlogoanimation);
        bottom=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.secondarylogoanimation);

        img1.setAnimation(top);
        tv1.setAnimation(bottom);
        tv2.setAnimation(bottom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();

            }
        }, 3000);
    }
}