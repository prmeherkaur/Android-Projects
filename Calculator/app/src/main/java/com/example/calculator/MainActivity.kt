package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException


class MainActivity : AppCompatActivity() {

    var lastNumeric=false
    var lastDot=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view: View){
        val tvInput:TextView=findViewById(R.id.tvInput)
        tvInput.append((view as Button).text) //Button inherits from view so we cast it
        lastNumeric=true
    }
    fun onClear(view: View){
        val tvInput:TextView=findViewById(R.id.tvInput)
        tvInput.text=""
    }
    fun onDecimal(view: View){
        if(lastNumeric && !lastDot){
            val tvInput:TextView=findViewById(R.id.tvInput)
            tvInput.append(".")
        }
    }
    fun onOperator(view:View){
        val tvInput:TextView=findViewById(R.id.tvInput)
        if(lastNumeric && !hasOperator(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }
    private fun removeZeroes(value:String) :String{
        return if(value.contains(".0")){
            value.substring(0,value.length-2)
        }else value
    }
    fun onEqual(view:View){
        val tvInput:TextView=findViewById(R.id.tvInput)
        try {
            if (lastNumeric) {
                var tvValue = tvInput.text.toString()
                var prefix = ""
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    var splitValue = tvValue.split("-")
                    var x = splitValue[0]
                    var y = splitValue[1]
                    if (!prefix.isEmpty()) {
                        x = "-" + x
                    }
                    tvInput.text = removeZeroes((x.toDouble() - y.toDouble()).toString())
                } else if (tvValue.contains("+")) {
                    var splitValue = tvValue.split("+")
                    var x = splitValue[0]
                    var y = splitValue[1]
                    if (!prefix.isEmpty()) {
                        x = "-" + x
                    }
                    tvInput.text = removeZeroes((x.toDouble() + y.toDouble()).toString())
                } else if (tvValue.contains("/")) {
                    var splitValue = tvValue.split("/")
                    var x = splitValue[0]
                    var y = splitValue[1]
                    if (!prefix.isEmpty()) {
                        x = "-" + x
                    }
                    tvInput.text = removeZeroes((x.toDouble() / y.toDouble()).toString())
                } else if (tvValue.contains("*")) {
                    var splitValue = tvValue.split("*")
                    var x = splitValue[0]
                    var y = splitValue[1]
                    if (!prefix.isEmpty()) {
                        x = "-" + x
                    }
                    tvInput.text = removeZeroes((x.toDouble() * y.toDouble()).toString())
                }
            }
        }catch(e:ArithmeticException){
            e.printStackTrace()
        }
    }
    private fun hasOperator(view :String):Boolean{
        return if(view.startsWith("-")){
            false
        }else{
            view.contains("+")|| view.contains("-")|| view.contains("*")|| view.contains("/")
        }
    }
}