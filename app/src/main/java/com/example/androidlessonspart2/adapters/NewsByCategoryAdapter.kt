package com.example.androidlessonspart2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.androidlessonspart2.databinding.HolderNewsByCategoryItemBinding
import com.example.androidlessonspart2.models.api.Data

class NewsByCategoryAdapter(private val onItemClick: (Data) -> Unit): RecyclerView.Adapter<NewsByCategoryViewHolder>() {

    private val data = arrayListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsByCategoryViewHolder {
        return NewsByCategoryViewHolder(
            HolderNewsByCategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClick
        )
    }

    override fun onBindViewHolder(holder: NewsByCategoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setList(list: List<Data>) {
        data.clear()
        data.addAll(list)
    }
}


class NewsByCategoryViewHolder(
    private val binding: HolderNewsByCategoryItemBinding,
    private val onItemClick: (Data) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Data) {
        binding.apply {
            title.text = model.title
            author.text = model.author
            image.load(model.imageUrl)
            itemView.setOnClickListener {
                onItemClick.invoke(model)
            }
        }
    }

}