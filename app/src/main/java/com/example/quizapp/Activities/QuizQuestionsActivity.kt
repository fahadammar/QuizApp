package com.example.quizapp.Activities

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizapp.Class.Question
import com.example.quizapp.Constants.Constants
import com.example.quizapp.R
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var questionList : ArrayList<Question>? = null
    private var currentPosition : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        // questionList will get the array containing the data class Question constructors with values initialized
        questionList = Constants.getQuestions()

        setQuestion()

        // Buttons onClick Listeners
        optionOne.setOnClickListener(this)
        optionTwo.setOnClickListener(this)
        optionThree.setOnClickListener(this)
        optionFour.setOnClickListener(this)

    }

    private fun setQuestion(){
        // Getting the question from the list with the help of current position.
        val question: Question? = questionList!![currentPosition - 1]

        // setting the question to default View
        setQuestionViewDefault()

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

    // this function is to set the choices to default properties (especially in their visible UI Text - Border)
    private fun setQuestionViewDefault(){
        // We Can make the array of the UI elements or View elements
        val options = ArrayList<TextView>()
        // Adding the TextView into Array
        options.add(0, optionOne)
        options.add(1, optionTwo)
        options.add(2, optionThree)
        options.add(3, optionFour)

        // looping through and changing the TextView colors, text style to say and TextView background
        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089")) // text color
            option.typeface = Typeface.DEFAULT // set the text to default (if text is bold it will become default - else also)
            // TextView background
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.custom_choice_bg
            )
        }

    }

    override fun onClick(v: View?) {
        Log.e("View.ID", v?.id.toString())
        Log.e("View.ID", "view ID of: " + v?.findViewById(v.id))
        // below the passed selectionOptionNum is basically the index of the Question in array ( index-1 )
        // to get to know about the v.id refer -> https://is.gd/2cQtKN
        when(v?.id){
            R.id.optionOne -> selectionOptionView(optionOne, 1)
            R.id.optionTwo -> selectionOptionView(optionTwo, 2)
            R.id.optionThree -> selectionOptionView(optionThree, 3)
            R.id.optionFour -> selectionOptionView(optionFour , 4)
        }
    }

    // changing the text (style basically to bold - default etc), background and color of the selected TextView
    private fun selectionOptionView(optionView : TextView, selectionOptionNum : Int ){
        setQuestionViewDefault() // reset all the buttons

        // then the selected button will go through below operation - which will change the selected option View.

        currentPosition = selectionOptionNum // the currentPosition number will be changed

        optionView.setTextColor(Color.parseColor("#363A43")) // change the color of the text in TextView
        // this another way for setting the text to Bold or default etc, using the Typeface
        optionView.setTypeface(optionView.typeface, Typeface.BOLD) // setting the text to Bold
        // changing the background of the TextView
        optionView.background = ContextCompat.getDrawable(
            this,
            R.drawable.custom_selectedchoice_bg
        )
    }


}