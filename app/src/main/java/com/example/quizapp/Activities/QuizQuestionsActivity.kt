package com.example.quizapp.Activities

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizapp.Class.Question
import com.example.quizapp.Constants.Constants
import com.example.quizapp.R
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var questionList : ArrayList<Question>? = null
    private var currentPosition : Int = 1
    private var selectedOption : Int = 0

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
        btnSubmit.setOnClickListener(this)

    }

    private fun setQuestion(){
        // Getting the question from the list with the help of current position.
        val question: Question? = questionList!![currentPosition - 1]

        // setting the question to default View
        setQuestionViewDefault()

        if(currentPosition == questionList!!.size){
            btnSubmit.text = "FINISH"
        } else {
            btnSubmit.text = "SUBMIT"
        }

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
            R.id.btnSubmit -> submitTapped()
        }
    }

    // changing the text (style basically to bold - default etc), background and color of the selected TextView
    private fun selectionOptionView(optionView : TextView, selectionOptionNum : Int ){
        setQuestionViewDefault() // reset all the buttons

        // then the selected button will go through below operation - which will change the selected option View.

        // selected option value will become the number the option is choosen or selected.
        selectedOption = selectionOptionNum

        optionView.setTextColor(Color.parseColor("#363A43")) // change the color of the text in TextView
        // this another way for setting the text to Bold or default etc, using the Typeface
        optionView.setTypeface(optionView.typeface, Typeface.BOLD) // setting the text to Bold
        // changing the background of the TextView
        optionView.background = ContextCompat.getDrawable(
            this,
            R.drawable.custom_selectedchoice_bg
        )
    }

    // this function is called in both conditions passing the values of the wrong choosen option and the right answer
    private fun answerView(answer: Int, drawableView: Int){
        // this function gets the right answer Int value and executes that statement
        // the function further called changes the TextView UI properties
        when(answer){
            1 -> changeResponseView(optionOne, drawableView)
            2 -> changeResponseView(optionTwo, drawableView)
            3 -> changeResponseView(optionThree, drawableView)
            4 -> changeResponseView(optionFour, drawableView)
        }
    }

    // this function changes the view of the Correct Answer and Wrong Answer
    private fun changeResponseView(view :TextView, drawableView: Int){
        view.background = ContextCompat.getDrawable(this, drawableView)
        if(drawableView == 2131165343) view.setTextColor(Color.parseColor("#FF000000"))
        else view.setTextColor(Color.parseColor("#FFFFFFFF"))
        view.setTypeface(view.typeface, Typeface.BOLD)
    }

    // this function is called when the submit button is tapped
    private fun submitTapped(){
        // At initial start the selectedOption value is equal to ZERO
        if(selectedOption == 0){
            // increment the currentPosition value - as in array list index new question will come
            currentPosition++

            when{
                // when the currentPosition values is less than equal to arrayList size
                currentPosition <=  questionList!!.size -> {
                    setQuestion() // setQuestion function will be called
                } else -> {
                Toast.makeText(this, "You have successfully completed the Quiz",
                    Toast.LENGTH_SHORT)
                    .show()
                }
            }
        }
        // else if the selectedOption is not equal to ZERO
        else {
            // getting the Question, with the currentPosition value - 1 (as index)
            val question = questionList?.get(currentPosition - 1)
            // if the correctAnswer number value is not equal to selectedOption number value.
            // this if condition won't execute if the choose option is the right one - Correct Answer
            if(question!!.correctAnswer != selectedOption){
                // when the answer is wrong, the wrong_answer drawable is passed
                // and that selectedOption num value is passed
                answerView(selectedOption, R.drawable.wrong_answer)
            }

            // it's despite of else, because it will happen everytime if or if not the answer selected is correct
            answerView(question.correctAnswer, R.drawable.correct_answer)

            // as the currentPosition value get's equal or greater than or less than arrayList size
            // submit button text changes
            if(currentPosition == questionList!!.size){
                btnSubmit.text = "FINISH"
            }else {
                btnSubmit.text = "GO TO THE NEXT QUESTION"
            }

            // at the end when new Question load, we set the selectedOption to ZERO
            // so the above If condition becomes true and currentPosition value increases
            // as in result we get the next question. As we tap again the submit button
            // when it's text is changed to "Go To the Next Question" at that time the
            // selectedOption number value is 0, above if condition triggers.
            selectedOption = 0
        }
    }

}