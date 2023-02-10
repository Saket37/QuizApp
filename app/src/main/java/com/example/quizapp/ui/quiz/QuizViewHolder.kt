package com.example.quizapp.ui.quiz

import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.ItemQuizOptionBinding

class QuizViewHolder(val binding: ItemQuizOptionBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun setOptions(options: String) {
        with(binding) {
            optionBtN.text = options
        }
    }
}