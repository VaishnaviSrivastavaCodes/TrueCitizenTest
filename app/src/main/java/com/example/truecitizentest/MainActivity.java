package com.example.truecitizentest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.truecitizentest.databinding.ActivityMainBinding;
import com.example.truecitizentest.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex =0;
    private Question[] questionbank = new Question[]{
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_citizenship, false),
            new Question(R.string.question_railways, true),
            new Question(R.string.question_chess, true),
            new Question(R.string.question_dposp, false),
            new Question(R.string.question_president, true),
            new Question(R.string.question_cji, true),
            new Question(R.string.question_borders, false),


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.questionTextView.setText(questionbank[currentQuestionIndex].getAnswerResId());

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("nextButton", "onClick:"+currentQuestionIndex);


                    currentQuestionIndex = (currentQuestionIndex+1)%questionbank.length;
                    updateQuestion(currentQuestionIndex);


            }
        });
        binding.prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("prevButton", "Current Index:"+currentQuestionIndex);
                if(currentQuestionIndex>0) {
                    currentQuestionIndex = (currentQuestionIndex-1)%questionbank.length;
                    updateQuestion(currentQuestionIndex);
                }

            }
        });

        binding.trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("trueButton", "User's Answer: True");
                checkAnswer(true);
            }
        });

        binding.falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("falseButton", "User's Answer: True");
                checkAnswer(false);
            }
        });
    }
    @SuppressLint("WrongConstant")
    private void checkAnswer(boolean choiceOfUser){
        boolean correctAnswer = questionbank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if(correctAnswer == choiceOfUser) {
            messageId = R.string.correct_answer;
    }
        else{
            messageId = R.string.wrong_answer;
    }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT)
                    .show();
    }

    private void updateQuestion(int currentQuestionIndex) {
        binding.questionTextView.setText(questionbank[currentQuestionIndex].getAnswerResId());
    }
}