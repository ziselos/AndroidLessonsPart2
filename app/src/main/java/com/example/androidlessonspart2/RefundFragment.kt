package com.example.androidlessonspart2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlessonspart2.databinding.FragmentRefundBinding


class RefundFragment : Fragment() {

    private var binding: FragmentRefundBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_refund, container, false)
        binding = FragmentRefundBinding.bind(view)
        return view
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
    companion object {
        @JvmStatic
        fun newInstance() = RefundFragment()
    }
}