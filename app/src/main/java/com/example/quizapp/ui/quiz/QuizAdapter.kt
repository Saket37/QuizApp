package com.example.quizapp.ui.quiz

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.ItemQuizOptionBinding
import javax.inject.Inject

class QuizAdapter @Inject constructor() : RecyclerView.Adapter<QuizViewHolder>() {
    var quizOptionsList: List<String> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var optionClicked: ((option: String) -> Unit) = fun(_: String) {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemQuizOptionBinding.inflate(inflater, parent, false)
        return QuizViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        with(quizOptionsList[position]) {
            holder.setOptions(this)
            holder.binding.optionBtN.setOnClickListener {
                optionClicked(this)
                Log.d("OPTION",this)
            }
        }
    }

    override fun getItemCount(): Int = quizOptionsList.size

}