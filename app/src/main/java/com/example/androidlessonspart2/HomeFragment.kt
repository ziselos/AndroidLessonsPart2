package com.example.androidlessonspart2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidlessonspart2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun initLayout() {
        binding?.apply {
           sales.setOnClickListener {
               findNavController().navigate(R.id.action_homeFragment_to_salesFragment)
           }

           refund.setOnClickListener {
               findNavController().navigate(R.id.action_homeFragment_to_refundFragment)
           }

           settings.setOnClickListener {
               findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
           }

       }
    }
}