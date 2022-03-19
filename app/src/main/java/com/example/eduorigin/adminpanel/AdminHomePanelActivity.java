package com.example.eduorigin.adminpanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eduorigin.R;
import com.example.eduorigin.registration.SignInActivity;

public class AdminHomePanelActivity extends AppCompatActivity {

    private Button adminLogOut;
    private TextView adminResponseWarning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Admin Panel");
        setContentView(R.layout.activity_admin_home_panel);


        adminLogOut=findViewById(R.id.adminLogoutButtonId);
        adminResponseWarning=findViewById(R.id.adminResponseWarningId);

        userExistenceCheck();


        adminLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.remove("email");
                editor.remove("password");
                editor.commit();
                editor.apply();

                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                finish();
            }
        });
    }
    private void  userExistenceCheck()
    {
        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        if(sp.contains("email"))
        {
            //works like navigation drawer username appears when user is login
            adminResponseWarning.setText(sp.getString("email",""));
        }
        //else{
        //    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        // }
    }



}