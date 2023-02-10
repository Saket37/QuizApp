package com.example.quizapp.data.quiz.impl

import com.example.quizapp.data.Result
import com.example.quizapp.models.Questions

class BlockingFakeQuizRepository : QuizRepository {
    override suspend fun getAllQuiz(): Result<List<Questions>> {
        return com.example.quizapp.data.Result.Success(quizQuestions)
    }
}