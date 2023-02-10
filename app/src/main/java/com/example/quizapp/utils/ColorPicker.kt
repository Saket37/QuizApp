package com.example.quizapp.utils

object ColorPicker {
    val colors = arrayOf("")
    var currentColorIndex =0
    fun getColor():String{
        currentColorIndex = (currentColorIndex +1) % colors.size
        return colors[currentColorIndex]
    }
}