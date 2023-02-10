package com.example.quizapp.data.quiz.impl

import com.example.quizapp.data.Result
import com.example.quizapp.models.Questions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeQuizRepository @Inject constructor() : QuizRepository {
    override suspend fun getAllQuiz(): Result<List<Questions>> {
        return withContext(Dispatchers.IO) {
            com.example.quizapp.data.Result.Success(quizQuestions)
        }
    }
}