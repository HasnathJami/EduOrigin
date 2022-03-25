package com.example.eduorigin.adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduorigin.QuizResultShowActivity;
import com.example.eduorigin.R;
import com.example.eduorigin.models.ResponseModelQuiz;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.viewHolder>{

    List<ResponseModelQuiz> data;


    public QuizAdapter(List<ResponseModelQuiz> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_quiz_design_template,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.singleRowQuizTitle.setText(data.get(position).getTitle());
        holder.singleRowQuizQuestion.setText(data.get(position).getQuestion());
        holder.singleRowQuizRadioButton1.setText(data.get(position).getOption_1());
        holder.singleRowQuizRadioButton2.setText(data.get(position).getOption_2());
        holder.singleRowQuizRadioButton3.setText(data.get(position).getOption_3());
        holder.singleRowQuizRadioButton4.setText(data.get(position).getOption_4());

        String correct_answer=data.get(position).getCorrect_answer();


        holder.singleRowQuizSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean radioButton1Result=holder.singleRowQuizRadioButton1.isChecked();
                Boolean radioButton2Result=holder.singleRowQuizRadioButton2.isChecked();
                Boolean radioButton3Result=holder.singleRowQuizRadioButton3.isChecked();
                Boolean radioButton4Result=holder.singleRowQuizRadioButton4.isChecked();

                String radioButton1Text=holder.singleRowQuizRadioButton1.getText().toString();
                String radioButton2Text=holder.singleRowQuizRadioButton2.getText().toString();
                String radioButton3Text=holder.singleRowQuizRadioButton3.getText().toString();
                String radioButton4Text=holder.singleRowQuizRadioButton4.getText().toString();

                Intent intent=new Intent(holder.singleRowQuizQuestion.getContext(), QuizResultShowActivity.class);
                intent.putExtra("correct_answer",correct_answer);
                intent.putExtra("radioButton1Result",radioButton1Result);
                intent.putExtra("radioButton2Result",radioButton2Result);
                intent.putExtra("radioButton3Result",radioButton3Result);
                intent.putExtra("radioButton4Result",radioButton4Result);
                intent.putExtra("recylerViewItemSizeForScoring",data.size());

                intent.putExtra("radioButton1Text",radioButton1Text);
                intent.putExtra("radioButton2Text",radioButton2Text);
                intent.putExtra("radioButton3Text",radioButton3Text);
                intent.putExtra("radioButton4Text",radioButton4Text);


                holder.singleRowQuizQuestion.getContext().startActivity(intent);
                holder.singleRowQuizSubmitButton.setEnabled(false);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView singleRowQuizTitle,singleRowQuizQuestion;
        //private RadioGroup singleRowQuizRadioGroup;
        private RadioButton singleRowQuizRadioButton1,singleRowQuizRadioButton2,singleRowQuizRadioButton3,singleRowQuizRadioButton4;
        private Button singleRowQuizSubmitButton,singleROwQuizShowResultButton;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            singleRowQuizTitle=itemView.findViewById(R.id.singleRowQuizTitileId);
            singleRowQuizQuestion=itemView.findViewById(R.id.singleRowQuizQuestionId);
            //singleRowQuizRadioGroup=itemView.findViewById(R.id.singleRowQuizRadioGroupId);
            singleRowQuizRadioButton1=itemView.findViewById(R.id.singleRowQuizRadioButton1Id);
            singleRowQuizRadioButton2=itemView.findViewById(R.id.singleRowQuizRadioButton2Id);
            singleRowQuizRadioButton3=itemView.findViewById(R.id.singleRowQuizRadioButton3Id);
            singleRowQuizRadioButton4=itemView.findViewById(R.id.singleRowQuizRadioButton4Id);
            singleRowQuizSubmitButton=itemView.findViewById(R.id.singleRowQuizSubmitButtonId);
            //singleROwQuizShowResultButton=itemView.findViewById(R.id.singleRowQuizShowResultButtonId);




        }
    }
}
