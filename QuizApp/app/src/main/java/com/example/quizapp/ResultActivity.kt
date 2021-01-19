package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    private var score:String=""
    private var total:String=""
    private var user:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        score=intent.getStringExtra(Constants.CORRECT_ANSWERS)!!
        total=intent.getStringExtra(Constants.TOTAL_QUESTIONS)!!
        user=intent.getStringExtra(Constants.USER_NAME)!!

        val scoreView=findViewById<TextView>(R.id.tv_score)
        scoreView.text="You got "+score+" out of "+total

        val userView=findViewById<TextView>(R.id.tv_name)
        userView.text=user

        val btnFin=findViewById<Button>(R.id.btn_finish)
        btnFin.setOnClickListener {
            val mainIntent= Intent(this,MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }

    }

}