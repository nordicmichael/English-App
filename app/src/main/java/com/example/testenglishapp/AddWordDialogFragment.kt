package com.example.testenglishapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.testenglishapp.databinding.DialogAddWordBinding

class AddWordDialogFragment : DialogFragment() {

    private var binding: DialogAddWordBinding? = null
    var wordsAdapter: WordsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogAddWordBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding?.btnAddWord?.setOnClickListener {

            wordsAdapter?.wordsList?.add(
                Word(
                    binding?.etWord?.text.toString(),
                    binding?.etTranslation?.text.toString()
                )
            )
            wordsAdapter?.notifyDataSetChanged()

            dialog?.dismiss()
        }
    }
}