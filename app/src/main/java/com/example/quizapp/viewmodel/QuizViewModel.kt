package com.example.quizapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.quiz.impl.FakeQuizRepository
import com.example.quizapp.models.Questions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class QuizState(
    val quiz: List<Questions> = emptyList(),
    val isLoading: Boolean = false,
    val userAnswer: String? = null,
    val questionIndex: Int = 0,
    val correctAnswer: Int = 0
)

@HiltViewModel
class MainViewModel @Inject constructor(private val quizRepository: FakeQuizRepository) :
    ViewModel() {
    private val _quizUiState = MutableStateFlow(QuizState())
    val quizUiState: StateFlow<QuizState> get() = _quizUiState

    init {
        observeData()
    }

    private fun observeData() {
        _quizUiState.value = _quizUiState.value.copy(isLoading = true)
        viewModelScope.launch {
            val result = quizRepository.getAllQuiz()
            Log.d("ViewModelInsert", result.toString())
            _quizUiState.update {
                when (result) {
                    is com.example.quizapp.data.Result.Success -> it.copy(
                        isLoading = false,
                        quiz = result.data.toMutableList()
                    )
                }
            }
        }
    }

    fun onNextClicked() {
        val index = _quizUiState.value.questionIndex
        correctAnswer(index)
        if (index < 4) {
            _quizUiState.value = _quizUiState.value.copy(questionIndex = index + 1)
        } else {
            return
        }
    }

    private fun correctAnswer(quizIndex: Int) {
        val userAnswer = _quizUiState.value.userAnswer
        val correctAnswer = _quizUiState.value.correctAnswer
        if (userAnswer == _quizUiState.value.quiz[quizIndex].answer) {
            _quizUiState.value = _quizUiState.value.copy(correctAnswer = correctAnswer + 1)
        }
        Log.d("CORRECT_ANSWER", _quizUiState.value.correctAnswer.toString())
    }

    fun onOptionClicked(option: String) {
        _quizUiState.value = _quizUiState.value.copy(userAnswer = option)
    }
}