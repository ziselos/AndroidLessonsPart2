package com.example.androidlessonspart2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.androidlessonspart2.databinding.HolderDisneyCharacterItemBinding
import com.example.androidlessonspart2.models.api.uiModels.disney.Data

class DisneyCharactersAdapter(private val onCharacterClicked: (Data) -> Unit) :
    RecyclerView.Adapter<DisneyCharacterViewHolder>() {

    private val charactersList = arrayListOf<Data>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DisneyCharacterViewHolder {
        return DisneyCharacterViewHolder(
            HolderDisneyCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onCharacterClicked
        )
    }

    override fun onBindViewHolder(
        holder: DisneyCharacterViewHolder,
        position: Int
    ) {
        holder.bind(charactersList[position])
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    fun setList(dataList: List<Data>) {
        charactersList.clear()
        charactersList.addAll(dataList)
    }
} // end of adapter

class DisneyCharacterViewHolder(private val binding: HolderDisneyCharacterItemBinding,
                                private val onCharacterClicked: (Data) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Data) {
        binding.apply {
            name.text = model.name
            info.text = model.url
            image.load(model.imageUrl)
            itemView.setOnClickListener {
                onCharacterClicked.invoke(model)
            }
        }
    }

}