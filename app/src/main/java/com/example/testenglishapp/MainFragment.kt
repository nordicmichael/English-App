package com.example.testenglishapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.testenglishapp.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var wordsAdapter: WordsAdapter? = null
    private var binding: FragmentMainBinding? = null
    private var sharedPreferences: SharedPreferences? = null
    private var dialogAddWord: AddWordDialogFragment = AddWordDialogFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        sharedPreferences = requireActivity().getSharedPreferences("SharedPreferences",
            Context.MODE_PRIVATE)

        setOnClickListeners()
        initWordsAdapter()
    }

    private fun initWordsAdapter() {

        wordsAdapter = WordsAdapter()
        binding?.rvWords?.adapter = wordsAdapter
        binding?.rvWords?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setOnClickListeners() {

        binding?.btnAddWords?.setOnClickListener {
            dialogAddWord.wordsAdapter = wordsAdapter

            dialogAddWord.show(requireActivity().supportFragmentManager, "MainFragment")
        }
    }

    override fun onStart() {

        if (sharedPreferences != null && wordsAdapter != null) {

            val wordsListJson = sharedPreferences?.getString("WORDS_LIST_KEY", "")
            var storedWordsList = Gson().fromJson<List<Word>>(
                wordsListJson,
                object : TypeToken<List<Word>>() {}.type
            )

            if (storedWordsList == null) {

                storedWordsList = mutableListOf()
            }

            wordsAdapter?.wordsList = storedWordsList.toMutableList()
        }

        super.onStart()
    }

    override fun onStop() {
        val wordsListJson = Gson().toJson(wordsAdapter?.wordsList)

        sharedPreferences?.edit()?.putString("WORDS_LIST_KEY", wordsListJson)?.apply()

        super.onStop()
    }
}