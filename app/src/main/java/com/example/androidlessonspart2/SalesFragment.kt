package com.example.androidlessonspart2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidlessonspart2.databinding.FragmentSalesBinding


class SalesFragment : Fragment() {

    private var binding: FragmentSalesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sales, container, false)
        binding = FragmentSalesBinding.bind(view)
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): SalesFragment  = SalesFragment()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}