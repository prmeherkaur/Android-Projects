package com.example.drawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class DrawingView(context:Context,attrs:AttributeSet) : View(context,attrs){
    private var drawPath:CustomPath?=null
    private var canvasBitmap: Bitmap?=null
    private var drawPaint: Paint?=null
    private var canvasPaint: Paint?=null
    private var brushSize:Float=0.toFloat()
    private var drawColor= Color.BLACK
    private var canvas:Canvas?=null
    init{
        setInitialVars()
    }
    private fun setInitialVars(){
        drawPaint=Paint()
        drawPath=CustomPath(drawColor,brushSize)
        drawPaint!!.color=drawColor
        drawPaint!!.style=Paint.Style.STROKE
        drawPaint!!.strokeJoin=Paint.Join.ROUND
        drawPaint!!.strokeCap=Paint.Cap.ROUND

        canvasPaint=Paint(Paint.DITHER_FLAG)
        /*Enabling this flag applies a dither(white noise-> to reduce distortion) to any blit operation where
        the target's colour space is more constrained than the source.*/

        brushSize=20.toFloat()
    }
    //If the size of screen changes, i.e when it is displayed everytime we  want to display the bitmap we want to draw on
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap= Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888) //Gives us millions of colors to use
        canvas= Canvas(canvasBitmap!!)
    }
    override  fun onDraw(canvas:Canvas?){
        super.onDraw(canvas)
        canvas!!.drawBitmap(canvasBitmap!!,0f,0f,canvasPaint) //gives us a size of exactly the visible screen,
        // can be given diff values if we want to expand or contract the size
        if(!drawPath!!.isEmpty){ //this is deciding what to do when we draw
            drawPaint!!.strokeWidth=drawPath!!.brushThickness
            drawPaint!!.color=drawPath!!.color
            canvas!!.drawPath(drawPath!!,drawPaint!!)
        }
    }
    //To determine when we will be drawing, i.e, on Touch
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val tX=event?.x
        val tY=event?.y
        //whenever there is a motion event one of three things can happpen
        when(event?.action){
            MotionEvent.ACTION_DOWN->{ // when we press
                drawPath!!.color=drawColor
                drawPath!!.brushThickness=brushSize
                //set everything there was previously to be empty
                drawPath!!.reset()
                if (tX!=null && tY!=null)drawPath!!.moveTo(tX!!,tY!!)
            }
            MotionEvent.ACTION_MOVE->{ //when we drag
                if (tX!=null && tY!=null)drawPath!!.lineTo(tX!!,tY!!)
            }
            MotionEvent.ACTION_UP->{ //when we stop
                drawPath=CustomPath(drawColor,brushSize) //finally display the path
            }
            else -> return false
        }
        invalidate()
        return true
    }
    internal inner class CustomPath(var color:Int,var brushThickness:Float):Path(){

    }
}