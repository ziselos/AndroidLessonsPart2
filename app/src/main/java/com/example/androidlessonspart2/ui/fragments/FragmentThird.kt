package com.example.androidlessonspart2.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlessonspart2.databinding.FragmentFirstBinding
import com.example.androidlessonspart2.databinding.FragmentSecondBinding
import com.example.androidlessonspart2.databinding.FragmentThirdBinding

class FragmentThird: Fragment() {

    companion object {
        fun newInstance(): FragmentThird {
            return FragmentThird()
        }
    }

    private var binding: FragmentThirdBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment", "Third Fragment > onCreateView called")
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    override fun onResume() {
        Log.d("Fragment", "Third Fragment > onResume called")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Fragment", "Third Fragment > onPause called")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("Fragment", "Third Fragment > onDestroy called")
        super.onDestroy()
        binding = null
    }

    private fun initLayout() {
        binding?.apply {
            nextButton.setOnClickListener {

            }
        }
    }
}