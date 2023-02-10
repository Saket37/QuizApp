package com.example.quizapp.models

data class Questions(
    val id: String,
    val question: String,
    val questionImage: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: String,
)
