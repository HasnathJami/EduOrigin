package com.example.eduorigin.featurefragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduorigin.QuizResultShowActivity;
import com.example.eduorigin.R;
import com.example.eduorigin.adapters.BookLibraryAdapter;
import com.example.eduorigin.adapters.QuizAdapter;
import com.example.eduorigin.controllers.ApiController;
import com.example.eduorigin.models.ResponseModelBookLibraryBackup;
import com.example.eduorigin.models.ResponseModelQuiz;
import com.example.eduorigin.models.ResponseModelQuizResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineQuizFragment extends Fragment {

    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    Context context;

    public OnlineQuizFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_online_quiz, container, false);


        SharedPreferences sp= getActivity().getSharedPreferences("credential",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        String emailForKey=sp.getString("email","");
        editor.putInt(emailForKey,0);
        editor.commit();
        editor.apply();


        recyclerView=view.findViewById(R.id.quizRecyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        processQuizData();


        return view;
    }



    private void processQuizData() {


        Call<List<ResponseModelQuiz>> call= ApiController.getInstance().getapi().getQuizData();
        call.enqueue(new Callback<List<ResponseModelQuiz>>() {
            @Override
            public void onResponse(Call<List<ResponseModelQuiz>> call, Response<List<ResponseModelQuiz>> response) {

                List<ResponseModelQuiz> data=response.body();
                //ResponseModelQuiz fetch=response.body();
                adapter=new QuizAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<ResponseModelQuiz>> call, Throwable t) {
                //Toast.makeText(, "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}