package com.example.eduorigin.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eduorigin.DashboardActivity;
import com.example.eduorigin.R;
import com.example.eduorigin.adminpanel.AdminHomePanelActivity;
import com.example.eduorigin.apicontrollers.ApiController;
import com.example.eduorigin.models.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private TextView tv1,responseWarning;
    private EditText emailLogin,passwordLogin;
    private Button loginButton;
    private final String adminEmail="admin@gmail.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        tv1=findViewById(R.id.askForRegisterInLoginActivityId);
        responseWarning=findViewById(R.id.responseWarningId);
        emailLogin=findViewById(R.id.emailLoginFieldId);
        passwordLogin=findViewById(R.id.passwordLoginFieldId);
        loginButton=findViewById(R.id.loginButtonId);



        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        userExistenceChecking();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loginProcess();

            }
        });


    }

       private void loginProcess()
       {
           String email=emailLogin.getText().toString();
           String password=passwordLogin.getText().toString();
           Call<ResponseModel> call=ApiController.getInstance().getapi().userVerification(email,password);
           call.enqueue(new Callback<ResponseModel>() {
               @Override
               public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                   ResponseModel obj=response.body();
                   String output=obj.getMessage().toString();

                   if(output.equals("exist"))
                   {

                       SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
                       SharedPreferences.Editor editor= sp.edit();
                       editor.putString("email",email);
                       editor.putString("password",password);
                       editor.commit();
                       editor.apply();



                       if(email.equals(adminEmail))
                       {
                           startActivity( new Intent(getApplicationContext(), AdminHomePanelActivity.class) );
                           finish();
                       }
                       else{
                           startActivity( new Intent(getApplicationContext(), DashboardActivity.class) );
                           finish();
                       }


                   }
                   if(output.equals("failed"))
                   {
                       emailLogin.setText("");
                       passwordLogin.setText("");
                       responseWarning.setTextColor(Color.RED);
                       responseWarning.setText("Invalid username or password");
                   }

               }

               @Override
               public void onFailure(Call<ResponseModel> call, Throwable t) {

                   responseWarning.setTextColor(Color.RED);
                   responseWarning.setText("Something Went wrong");
               }
           });
       }

       private void  userExistenceChecking()
       {

           SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
           String email=emailLogin.getText().toString();
           if(sp.contains("email") && !email.equals("admin@gmail.com") )

           {
               startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
           }
           else if(sp.contains("email") && email.equals(adminEmail) )
           {
               startActivity(new Intent(getApplicationContext(),AdminHomePanelActivity.class));
           }

           else{

               responseWarning.setText("Please Login");
               responseWarning.setTextColor(Color.RED);

           }
       }
}