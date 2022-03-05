package com.example.androidlessonspart2.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlessonspart2.adapters.DisneyCharactersAdapter
import com.example.androidlessonspart2.api.APIInterface
import com.example.androidlessonspart2.api.DisneyAPIClient
import com.example.androidlessonspart2.databinding.ActivityDisneyCharactersBinding
import com.example.androidlessonspart2.models.api.uiModels.disney.Data
import kotlinx.coroutines.*

class DisneyCharactersActivity : AppCompatActivity() {


    companion object {
        const val DISNEY_CHARACTER_DETAILS = "DISNEY_CHARACTER_DETAILS"
    }


    private lateinit var binding: ActivityDisneyCharactersBinding

    private lateinit var adapter: DisneyCharactersAdapter

    private var apiInterface: APIInterface? = null
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisneyCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO: make api call to disney url and display data
        initRecyclerView()
        fetchDisneyCharacters()
    }

    private fun fetchDisneyCharacters() {
        apiInterface = DisneyAPIClient.disneyClient?.create(APIInterface::class.java)
        binding.loader.visibility = View.VISIBLE
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiInterface?.getDisneyCharacters()
            withContext(Dispatchers.Main) {
                // hide loader
                binding.loader.visibility = View.GONE
                if (response?.isSuccessful == true) {
                    // update the UI
                    adapter.setList(response.body()?.data as ArrayList<Data>)
                    adapter.notifyDataSetChanged()
                    Log.d("Disney", response.body()?.data?.size.toString())
                } else {
                    // error handling
                    Log.d("Disney", response?.errorBody().toString())
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = DisneyCharactersAdapter(onCharacterClicked = { details ->
            Intent(this, DisneyCharacterDetailsActivity::class.java).also {
                it.putExtra(DISNEY_CHARACTER_DETAILS, details)
                startActivity(it)
            }
        })
        binding.disneyRecyclerView.adapter = adapter
    }


}