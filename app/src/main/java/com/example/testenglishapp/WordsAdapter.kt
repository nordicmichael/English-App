package com.example.testenglishapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testenglishapp.databinding.RvWordsItemBinding

class WordsAdapter : RecyclerView.Adapter<WordsAdapter.WordViewHolder>() {

    var wordsList: MutableList<Word> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {

        return WordViewHolder(
            RvWordsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {

        return wordsList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {

        holder.setOnClickListeners()
        holder.bind()
    }

    inner class WordViewHolder(val binding: RvWordsItemBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        private var isTranslationShown = false

        fun setOnClickListeners() {

            itemView.setOnClickListener {

                if (isTranslationShown) {

                    binding.tvWord.setTextColor(itemView.context.getColor(R.color.black))
                    binding.tvWord.text = wordsList[adapterPosition].word

                    isTranslationShown = false
                } else {
                    binding.tvWord.setTextColor(itemView.context.getColor(R.color.light_blue))
                    binding.tvWord.text = wordsList[adapterPosition].translation

                    isTranslationShown = true
                }
            }

            binding.ivDeleteWord.setOnClickListener {

                wordsList.removeAt(position)
                notifyItemRemoved(position)
            }
        }

        fun bind() {
            binding.tvWord.setTextColor(itemView.context.getColor(R.color.black))
            binding.tvWord.text = wordsList[adapterPosition].word
        }
    }
}