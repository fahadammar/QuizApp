package com.example.quizapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quizapp.Class.Question
import com.example.quizapp.Constants.Constants
import com.example.quizapp.R
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        // questionList will get the array containing the data class Question constructors with values initialized
        val questionList = Constants.getQuestions()
        Log.i("QuestionList", "Size => ${questionList.size}" )


        val currentPosition = 1
        val question: Question? = questionList[currentPosition - 1]

        // progressBar id -> accessing the property progess & progressBar.max
        progressBar.progress = currentPosition
        // Number which is right to progressBar
        progressNum.text = "$currentPosition" + "/" + progressBar.max
        // top question text ID
        questionText.text = question!!.question
        // flag image
        flagImage.setImageResource(question.image) // the image is Int basically - for further info do search about it
        // choice ID's
        optionOne.text = question.optionOne
        optionTwo.text = question.optionTwo
        optionThree.text = question.optionThree
        optionFour.text = question.optionFour
    }
}