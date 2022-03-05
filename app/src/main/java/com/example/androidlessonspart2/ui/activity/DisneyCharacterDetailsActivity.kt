package com.example.androidlessonspart2.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.androidlessonspart2.databinding.ActivityDisneyCharacterDetailsBinding
import com.example.androidlessonspart2.models.api.uiModels.disney.Data
import kotlinx.android.synthetic.main.holder_disney_character_item.*

class DisneyCharacterDetailsActivity: AppCompatActivity() {

    companion object {
        const val DISNEY_CHARACTER_DETAILS = "DISNEY_CHARACTER_DETAILS"
    }

    private lateinit var binding: ActivityDisneyCharacterDetailsBinding

    private var disneyModelDetails: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisneyCharacterDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras.apply {
            disneyModelDetails = this?.getParcelable(DISNEY_CHARACTER_DETAILS)
        }
        setUI(disneyModelDetails)
    }

    private fun setUI(disneyModelDetails: Data?) {
        binding.apply {
            name.text = disneyModelDetails?.name
            // TODO: make an example of string resource usage
            id.text = "ID: ${disneyModelDetails?._id}"
            url.text = "URL: ${disneyModelDetails?.url}"
            image.load(disneyModelDetails?.imageUrl)
        }
    }

}