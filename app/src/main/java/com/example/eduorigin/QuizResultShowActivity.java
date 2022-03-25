package com.example.eduorigin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStateManagerControl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import com.example.eduorigin.controllers.ApiController;
import com.example.eduorigin.featurefragments.OnlineQuizFragment;
import com.example.eduorigin.models.ResponseModelQuiz;
import com.example.eduorigin.models.ResponseModelQuizResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizResultShowActivity extends AppCompatActivity {


    private TextView quizResultShowEmail,quizResultShowResult,quizResultShowVerdict,quizCorrectAnswerShow;
    private String verdict="";
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    private ImageView quizResultVerdictIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result_show);

        toolbar=findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        SharedPreferences.Editor editor= sp.edit();

        String emailForKey=sp.getString("email","");


       // editor.putBoolean("submitButtonIsCheckedFalse",false);
        //editor.commit();
       // editor.apply();

        int result=sp.getInt(emailForKey,0);

        //quizResultShowEmail=findViewById(R.id.quizResultShowEmailId);
        quizResultShowResult=findViewById(R.id.quizResultShowResultId);
        quizResultShowVerdict=findViewById(R.id.quizResultShowVerdictId);
        quizResultVerdictIcon=findViewById(R.id.verdictIconId);
        quizCorrectAnswerShow=findViewById(R.id.quizCorrectAnswerShowId);

        String correct_answer=getIntent().getStringExtra("correct_answer");
        Boolean radioButton1Result=getIntent().getExtras().getBoolean("radioButton1Result");
        Boolean radioButton2Result=getIntent().getExtras().getBoolean("radioButton2Result");
        Boolean radioButton3Result=getIntent().getExtras().getBoolean("radioButton3Result");
        Boolean radioButton4Result=getIntent().getExtras().getBoolean("radioButton4Result");
        int recyclerViewItemSizeForScoring=getIntent().getIntExtra("recylerViewItemSizeForScoring",0);


        String radioButton1Text=getIntent().getStringExtra("radioButton1Text");
        String radioButton2Text=getIntent().getStringExtra("radioButton2Text");
        String radioButton3Text=getIntent().getStringExtra("radioButton3Text");
        String radioButton4Text=getIntent().getStringExtra("radioButton4Text");
        String showCorrectAnswer="";

        if(recyclerViewItemSizeForScoring>result)
        {


            if(radioButton1Result==true && correct_answer.equals("1"))
            {

                verdict = "Correct Answer";
                showCorrectAnswer=radioButton1Text;
                result=result+1;
                editor.putInt(emailForKey,result);
                editor.commit();
                editor.apply();


            }


            else if(radioButton2Result==true && correct_answer.equals("2"))
            {
                verdict = "Correct Answer";
                showCorrectAnswer=radioButton2Text;
                result=result+1;
                editor.putInt(emailForKey,result);
                editor.commit();
                editor.apply();


            }

            else if(radioButton3Result==true && correct_answer.equals("3"))
            {
                verdict = "Correct Answer";
                showCorrectAnswer=radioButton3Text;
                result=result+1;
                editor.putInt(emailForKey,result);
                editor.commit();
                editor.apply();


            }

            else if(radioButton4Result==true && correct_answer.equals("4"))
            {
                verdict = "Correct Answer";
                showCorrectAnswer=radioButton4Text;
                result=result+1;
                editor.putInt(emailForKey,result);
                editor.commit();
                editor.apply();


            }

            else{

                verdict="Wrong Answer";

            }

        }

        else {

                if (radioButton1Result == true && correct_answer.equals("1")) {

                    verdict = "Correct Answer";


                } else if (radioButton2Result == true && correct_answer.equals("2")) {
                    verdict = "Correct Answer";


                } else if (radioButton3Result == true && correct_answer.equals("3")) {
                    verdict = "Correct Answer";


                } else if (radioButton4Result == true && correct_answer.equals("4")) {
                    verdict = "Correct Answer";


                } else {

                    verdict = "Wrong Answer";

                }


        }



        if(correct_answer.equals("1"))
            showCorrectAnswer="A";

        else if(correct_answer.equals("2"))
            showCorrectAnswer="B";

        else if(correct_answer.equals("3"))
            showCorrectAnswer="C";

        else
            showCorrectAnswer="D";






        if(verdict=="Correct Answer")
        {
            String[] name=emailForKey.split("@");
            quizResultShowVerdict.setText(verdict);
            quizResultShowResult.setText("Total Score : "+String.valueOf(result)+"/"+recyclerViewItemSizeForScoring);
        }

        else{

            quizResultVerdictIcon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wrong_answer));
            quizResultShowVerdict.setText(verdict);
            quizResultShowResult.setText("Total Score : "+String.valueOf(result)+"/"+recyclerViewItemSizeForScoring);
            quizCorrectAnswerShow.setText("Correct Answer: "+showCorrectAnswer);

        }


        sendQuizResultToDB();

    }


    private void sendQuizResultToDB() {



        SharedPreferences sp=getSharedPreferences("credential",MODE_PRIVATE);
        String email=sp.getString("email","");

        int spResult=sp.getInt(email,0);
        Call<ResponseModelQuizResult> call= ApiController.getInstance().getapi().submitQuizResult(email,spResult,verdict);

        call.enqueue(new Callback<ResponseModelQuizResult>() {
            @Override
            public void onResponse(Call<ResponseModelQuizResult> call, Response<ResponseModelQuizResult> response) {

            }

            @Override
            public void onFailure(Call<ResponseModelQuizResult> call, Throwable t) {

                Toast.makeText(QuizResultShowActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });

    }




    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();

        //Intent intent=new Intent(getApplicationContext(),OnlineQuizFragment.class);
       // startActivity(intent);
        finish();
        return super.onSupportNavigateUp();
    }

}
