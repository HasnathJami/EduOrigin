package com.example.eduorigin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eduorigin.registration.SignInActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button signoutButton;
    private TextView responseWarningDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        signoutButton=findViewById(R.id.logoutButtonId);
        responseWarningDashboard=findViewById(R.id.responseWarningDashboardId);

        userExistenceCheck();


        signoutButton.setOnClickListener(new View.OnClickListener() {
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
             responseWarningDashboard.setText(sp.getString("email",""));
         }
         //else{
         //    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        // }
     }
}