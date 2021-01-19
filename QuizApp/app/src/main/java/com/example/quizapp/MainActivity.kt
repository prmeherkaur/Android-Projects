package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onStartClick(view: View){
        val et=findViewById<EditText>(R.id.etName)
        if(et.text.toString().isEmpty()){
            Toast.makeText(this, "Cannot accept empty Name",Toast.LENGTH_SHORT).show()
        }else{
            val intent= Intent(this,QuizQuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME,et.text.toString())
            startActivity(intent)
            finish()
        }
    }
}