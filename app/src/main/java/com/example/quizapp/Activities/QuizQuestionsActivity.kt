package com.example.quizapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizapp.Constants.Constants
import com.example.quizapp.R

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        val questionList = Constants.getQuestions()
        Log.i("QuestionList", "Size => ${questionList.size}" )
    }
}