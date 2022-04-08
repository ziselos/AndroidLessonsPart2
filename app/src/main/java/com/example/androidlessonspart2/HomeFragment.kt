package com.example.androidlessonspart2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidlessonspart2.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    private var homeBinding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        val binding = FragmentHomeBinding.bind(view)
        homeBinding = binding
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    override fun onDestroy() {
        homeBinding = null
        super.onDestroy()
    }

    private fun initLayout() {
       homeBinding?.apply {
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