package com.example.quizapp.data

import android.content.Context
import com.example.quizapp.data.quiz.impl.FakeQuizRepository
import com.example.quizapp.data.quiz.impl.QuizRepository

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val quizRepository: QuizRepository
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val quizRepository: QuizRepository
            by lazy { FakeQuizRepository() }

}