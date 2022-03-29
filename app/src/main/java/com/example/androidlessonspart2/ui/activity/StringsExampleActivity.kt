package com.example.androidlessonspart2.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessonspart2.R
import com.example.androidlessonspart2.databinding.ActivityStringsExampleBinding

class StringsExampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStringsExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStringsExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        binding.apply {
            button.setOnClickListener {
                Toast.makeText(this@StringsExampleActivity,
                    resources.getString(R.string.demo_screen_toast_message), Toast.LENGTH_LONG).show()
            }
        }
    }
}