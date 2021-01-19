package com.example.quizapp
// made to describe the basic framework of a question in Quiz app
data class Question (
    val id:Int,
    val question:String,
    val img: Int,
    val opt1:String,
    val opt2:String,
    val opt3:String,
    val opt4:String,
    val correctAns:Int
)