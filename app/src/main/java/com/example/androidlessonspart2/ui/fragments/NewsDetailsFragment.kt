package com.example.androidlessonspart2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidlessonspart2.databinding.FragmentFirstBinding
import com.example.androidlessonspart2.databinding.FragmentNewsDetailsBinding
import com.example.androidlessonspart2.models.api.Data

class NewsDetailsFragment: Fragment() {

    private var article: Data? = null

    companion object {
        const val ARTICLE_ARG = "ARTICLE"
        @JvmStatic
        fun newInstance(article: Data): NewsDetailsFragment {
            val fragment = NewsDetailsFragment()
            Bundle().apply {
                putParcelable(ARTICLE_ARG, article)
            }.also {
                fragment.arguments = it
            }
            return fragment
        }
    }


    private var binding: FragmentNewsDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun initLayout() {
        article = arguments?.getParcelable(ARTICLE_ARG)
        binding?.apply {
            title.text = article?.title
            author.text = article?.author
            date.text = article?.date
            content.text = article?.content
        }
    }
}