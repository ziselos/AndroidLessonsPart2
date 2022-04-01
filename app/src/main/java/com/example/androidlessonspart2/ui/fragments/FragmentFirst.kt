package com.example.androidlessonspart2.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlessonspart2.R
import com.example.androidlessonspart2.databinding.FragmentFirstBinding

class FragmentFirst: Fragment() {

    companion object {
        fun newInstance(): FragmentFirst {
            return FragmentFirst()
        }
    }

    private var binding: FragmentFirstBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Fragment", "First Fragment > onCreateView called")
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    override fun onResume() {
        Log.d("Fragment", "First Fragment > onResume called")
        super.onResume()
    }

    override fun onPause() {
        Log.d("Fragment", "First Fragment > onPause called")
        super.onPause()
    }

    override fun onDestroy() {
        Log.d("Fragment", "First Fragment > onDestroy called")
        super.onDestroy()
        binding = null
    }

    private fun initLayout() {
        binding?.apply {
            nextButton.setOnClickListener {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.fragmentContainer, FragmentSecond.newInstance())
                    ?.addToBackStack("tag")
                    ?.commitAllowingStateLoss()
            }
        }
    }
}