package com.simplekjl.news.presentation.news

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.simplekjl.news.R
import com.simplekjl.news.presentation.news.adapter.NewsAdapter
import com.simplekjl.news.presentation.news.adapter.ViewDisplayType
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private val newsAdapter = NewsAdapter()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val orientation = resources.configuration.orientation
        var spanCount = 2
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            spanCount = 3 // for the case of having it in landscape
        } else {
            // In portrait
            spanCount = 2
        }
        val glm = GridLayoutManager(context, spanCount)
        glm.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (getTypeForPosition(position)) {
                    is ViewDisplayType.HeaderItem -> 1
                    is ViewDisplayType.GridItem -> spanCount
                }
            }
        }
        newRv.apply {
            layoutManager = glm
            adapter = newsAdapter
        }
        viewModel.uiStateLiveData.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        viewModel.getNews()
    }

    private fun getTypeForPosition(position: Int): ViewDisplayType {
        // rem calculates the remainder
        return when (position.rem(7)) {
            0 -> ViewDisplayType.GridItem
            else -> ViewDisplayType.HeaderItem
        }
    }

    private fun renderState(state: UiState) {
        when (state) {
            UiState.Loading -> {

            }
            is UiState.OnSuccessNews -> {
                newsAdapter.updateNews(state.data.articles)
            }
            UiState.Error -> {

            }
        }
    }

}
