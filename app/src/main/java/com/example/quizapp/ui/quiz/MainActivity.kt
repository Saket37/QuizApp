package com.example.quizapp.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.ui.result.ResultActivity
import com.example.quizapp.viewmodel.MainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var quizAdapter: QuizAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRV()
        setUpViews()
    }

    private fun setUpRV() {
        binding.quiz.optionsRV.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = quizAdapter
        }
    }

    private fun setUpViews() {
        lifecycleScope.launch {
            mainViewModel.quizUiState.collectLatest { quizState ->
                if (quizState.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    try {
                        Picasso.get().load(quizState.quiz[quizState.questionIndex].questionImage)
                            .into(binding.quiz.quizIV)
                    } catch (e: Exception) {
                        Toast.makeText(this@MainActivity, "Error Loading Image", Toast.LENGTH_SHORT)
                            .show()
                    }
                    binding.horizontalProgressBar.apply {
                        progress = quizState.questionIndex + 1
                        max = quizState.quiz.size
                    }
                    binding.progressText.text = getString(
                        R.string.progress, quizState.questionIndex + 1, quizState.quiz.size
                    )
                    binding.progressBar.visibility = View.GONE
                    binding.quiz.questionTV.text = quizState.quiz[quizState.questionIndex].question
                    binding.quiz.questionNoTV.text =
                        getString(R.string.question_no, quizState.quiz[quizState.questionIndex].id)
                    quizAdapter.quizOptionsList = listOf(
                        quizState.quiz[quizState.questionIndex].option1,
                        quizState.quiz[quizState.questionIndex].option2,
                        quizState.quiz[quizState.questionIndex].option3,
                        quizState.quiz[quizState.questionIndex].option4,
                    )
                    if (quizState.questionIndex < 4) {
                        binding.nextBTN.text = getString(R.string.next_btn)
                    } else {
                        binding.nextBTN.text = getString(R.string.submit_btn)

                    }
                    binding.nextBTN.setOnClickListener {
                        animateLayout()
                        mainViewModel.onNextClicked()
                        if (quizState.questionIndex == 4) {
                            openResultActivity()
                        }
                    }
                }
                quizAdapter.optionClicked = fun(option: String) {
                    mainViewModel.onOptionClicked(option)
                }
            }
        }
    }

    private fun animateLayout() {
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        binding.quiz.root.startAnimation(fadeOut)
    }

    private fun openResultActivity() {
        lifecycleScope.launch {
            mainViewModel.quizUiState.collectLatest {
                Intent(this@MainActivity, ResultActivity::class.java).run {
                    putExtra("Answer", it.correctAnswer.toString())
                    Log.d("ANSWER", it.correctAnswer.toString())
                    startActivity(this)
                }
            }
        }

    }
}