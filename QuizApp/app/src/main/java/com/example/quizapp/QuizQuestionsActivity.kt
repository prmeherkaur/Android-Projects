package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var questionsList:ArrayList<Question>?=null
    private var currentPosition=1
    private var selectedOption=0
    private var correctAnswers=0
    private var o1:TextView?=null
    private var o2:TextView?=null
    private var o3:TextView?=null
    private var o4:TextView?=null
    private var ques:TextView?=null
    private var pb:ProgressBar?=null
    private var pbText:TextView?=null
    private var image:ImageView?=null
    private var UserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)
        questionsList=Constants.getQuestions()

        UserName=intent.getStringExtra(Constants.USER_NAME)

        ques = findViewById(R.id.tvQues)
        pb=findViewById(R.id.progressBar)
        pbText=findViewById(R.id.tv_progress)
        image=findViewById(R.id.ivImage)
        o1=findViewById(R.id.tv01)
        o2=findViewById(R.id.tvO2)
        o3=findViewById(R.id.tvO3)
        o4=findViewById(R.id.tvO4)

        setQuestions()
        val btnSubmit=findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener(this)
        o1!!.setOnClickListener(this)
        o2!!.setOnClickListener(this)
        o3!!.setOnClickListener(this)
        o4!!.setOnClickListener(this)
    }

    private fun setQuestions(){
        val question =
                questionsList!![currentPosition - 1] // Getting the question from the list with the help of current position.

        defaultOptionsView()
        val btnSubmit=findViewById<Button>(R.id.btnSubmit)


        pb!!.progress = currentPosition
        pbText!!.text = "$currentPosition" + "/" + "${pb!!.getMax()}"

        ques!!.text = question.question
        image!!.setImageResource(question.img)
        o1!!.text = question.opt1
        o2!!.text = question.opt2
        o3!!.text = question.opt3
        o4!!.text = question.opt4
        if(currentPosition==questionsList!!.size) btnSubmit.text="FINISH"
        else btnSubmit.text="SUBMIT"

    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, o1!!)
        options.add(1, o2!!)
        options.add(2, o3!!)
        options.add(3, o4!!)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    R.drawable.default_option_border_bg
            )
        }
    }
    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.tv01 -> {

                selectedOptionView(o1!!, 1)
            }

            R.id.tvO2 -> {

                selectedOptionView(o2!!, 2)
            }

            R.id.tvO3 -> {

                selectedOptionView(o3!!, 3)
            }

            R.id.tvO4 -> {

                selectedOptionView(o4!!, 4)
            }
            R.id.btnSubmit->{
                val btnSubmit=findViewById<Button>(R.id.btnSubmit)
                if(selectedOption!=0) {
                    val question =
                            questionsList!![currentPosition - 1]
                    setAnswerBg(question.correctAns, R.drawable.corrected_option_border_bg)
                    if (selectedOption != question.correctAns) setAnswerBg(selectedOption, R.drawable.wrong_option_border_bg)
                    else correctAnswers++
                    if(currentPosition==questionsList!!.size) btnSubmit.text="FINISH"
                    else btnSubmit.text="MOVE TO NEXT QUESTION"
                    selectedOption=0
                }else{
                    currentPosition++
                    if(currentPosition<=questionsList!!.size){
                        setQuestions()
                    }else{
                        val intent= Intent(this,ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, UserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers.toString())
                        intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList!!.size.toString())
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
    private fun setAnswerBg(answer:Int,drawableView:Int){
        when(answer){
            1->{
                o1!!.background=ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                o2!!.background=ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                o3!!.background=ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                o4!!.background=ContextCompat.getDrawable(this,drawableView)
            }
        }
    }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        selectedOption= selectedOptionNum

        tv.setTextColor(
                Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.selected_option_border_bg
        )
    }

}