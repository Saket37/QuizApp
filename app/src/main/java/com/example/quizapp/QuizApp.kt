package com.example.quizapp

import android.app.Application
import com.example.quizapp.data.AppContainer
import com.example.quizapp.data.AppContainerImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class QuizApp : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}