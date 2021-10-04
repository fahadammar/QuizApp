package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.Activities.QuizQuestionsActivity
import com.example.quizapp.Constants.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCheckIfEmpty(view: View){
        if(edit_text.text.toString().isEmpty()){
            Toast.makeText(this, "Please Insert Your Name", Toast.LENGTH_LONG).show()
        }
        else {
            var intent = Intent(this, QuizQuestionsActivity::class.java)
            startActivity(intent)
            finish() // finishes this activity
        }
    }

}