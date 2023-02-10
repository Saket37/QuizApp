package com.example.quizapp.data.quiz.impl

import com.example.quizapp.data.Result
import com.example.quizapp.models.Questions

interface QuizRepository {
    suspend fun getAllQuiz(): Result<List<Questions>>
}