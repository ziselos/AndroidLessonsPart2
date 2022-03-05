package com.example.androidlessonspart2.adapters

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidlessonspart2.databinding.HolderNewsCategoryRoundedItemBinding
import com.example.androidlessonspart2.databinding.HolderNewsCategoryRowBinding
import kotlinx.android.parcel.Parcelize

class NewsCategoriesAdapter(private val onCategoryClicked: (NewsCategory) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_NORMAL = 0
        const val TYPE_ROUNDED = 1
    }

    private val categoriesList = arrayListOf<NewsCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_NORMAL -> {
                return NewsCategoriesViewHolder(
                    HolderNewsCategoryRowBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    ), onCategoryClicked
                )
            }
            else -> {
                return NewsCategoryRoundedViewHolder(
                    HolderNewsCategoryRoundedItemBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    ), onCategoryClicked
                )
            }
        }
    }

    // please do not add here any click listener
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NewsCategoriesViewHolder) {
            val normalHolder: NewsCategoriesViewHolder = holder
            normalHolder.bind(categoriesList[position])
        }
        if (holder is NewsCategoryRoundedViewHolder) {
            val roundedHolder: NewsCategoryRoundedViewHolder = holder
            roundedHolder.bind(categoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            1, 5, 8 -> TYPE_ROUNDED
            else -> TYPE_NORMAL
        }
    }

    fun setList(list: List<NewsCategory>) {
        categoriesList.clear()
        categoriesList.addAll(list)
    }
}

class NewsCategoriesViewHolder(
    private val binding: HolderNewsCategoryRowBinding,
    private val onCategoryClicked: (NewsCategory) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: NewsCategory) {
        binding.apply {
            categoryButton.text = model.title
            categoryButton.setOnClickListener {
                onCategoryClicked.invoke(model)
            }
        }
    }
}

class NewsCategoryRoundedViewHolder(
    private val binding: HolderNewsCategoryRoundedItemBinding,
    private val onCategoryClicked: (NewsCategory) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: NewsCategory) {
        binding.apply {
            category.text = model.title
            itemView.setOnClickListener {
                onCategoryClicked.invoke(model)
            }
        }
    }
}

@Parcelize
data class NewsCategory(
    val title: String
): Parcelable
