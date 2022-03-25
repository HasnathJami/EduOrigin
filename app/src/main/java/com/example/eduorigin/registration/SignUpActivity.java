package com.example.eduorigin.registration;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eduorigin.R;
import com.example.eduorigin.controllers.ApiController;
import com.example.eduorigin.models.ResponseModelRegistration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

     EditText nameRegister,emailRegister,passwordRegister;
     Button registerButton;
    // public static final String url="http://192.168.0.104/EduOriginAPI/Registration/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        nameRegister=findViewById(R.id.nameRegisterFieldId);
        emailRegister=findViewById(R.id.emailRegisterFieldId);
        passwordRegister=findViewById(R.id.passwordRegisterFieldId);
        registerButton=findViewById(R.id.registerButtonId);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_user(nameRegister.getText().toString().trim(),emailRegister.getText().toString().trim(),passwordRegister.getText().toString().trim());

            }
        });

    }

    public void register_user(final String name,final String email,final String password)
    {
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(SignUpActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(SignUpActivity.this,error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> map=new HashMap<String,String>();
//
//                map.put("name",name);
//                map.put("email",email);
//                map.put("password",password);
//
//                return map;
//            }
//        };
//
//        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);

        Call<ResponseModelRegistration> call= ApiController.getInstance().getapi().sendRegistrationData(name,email,password);

        call.enqueue(new Callback<ResponseModelRegistration>() {
            @Override
            public void onResponse(Call<ResponseModelRegistration> call, Response<ResponseModelRegistration> response) {

                ResponseModelRegistration obj= response.body();

                String output=obj.getMessage();

                if(output.equals("inserted"))
                {
                    Toast.makeText(SignUpActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                }
                if(output.equals("failed"))
                    Toast.makeText(SignUpActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseModelRegistration> call, Throwable t) {

                Toast.makeText(SignUpActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });





    }
}