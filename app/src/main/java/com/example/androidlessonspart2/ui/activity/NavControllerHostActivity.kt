package com.example.androidlessonspart2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.androidlessonspart2.R
import com.example.androidlessonspart2.databinding.ActivityNavControllerHostBinding

class NavControllerHostActivity: AppCompatActivity() {

    private lateinit var binding: ActivityNavControllerHostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavControllerHostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initLayout()
    }

    //override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.container).navigateUp()

    private fun initLayout() {
        val host = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction().replace(R.id.demoNavigationHostFragment, host).setPrimaryNavigationFragment(host).commit()
    }

}