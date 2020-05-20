package com.simplekjl.news.presentation.news

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.simplekjl.news.R
import com.simplekjl.news.domain.model.NewsEntity
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
        setupRecyclerView()
        viewModel.uiStateLiveData.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })
        viewModel.getNews(newsAdapter.itemCount)
    }

    private fun setupRecyclerView() {
        val orientation = resources.configuration.orientation
        val spanCount = if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            3 // for the case of having it in landscape
        } else {
            // In portrait
            2
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
        newsRv.layoutManager = glm
        newsRv.adapter = newsAdapter
        newsRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = glm.itemCount
                if (totalItemCount == glm.findLastVisibleItemPosition() + 1) {
                    viewModel.getNews(newsAdapter.itemCount)
                }
            }
        })
    }

    private fun getTypeForPosition(position: Int): ViewDisplayType {
        // rem calculates the remainder
        return when (position.rem(7)) {
            0 -> ViewDisplayType.GridItem
            else -> ViewDisplayType.HeaderItem
        }
    }

    private fun renderState(state: UiState) {
        // variables to avoid repeated state when loading more data
        val shouldShowToast = newsAdapter.itemCount != 0
        val shouldShowLoading = newsAdapter.itemCount != 0
        when (state) {
            UiState.Loading -> {
                showLoading(shouldShowLoading)
            }
            is UiState.OnSuccessNews -> {
                showNews(state.data)
            }
            is UiState.Error -> {
                showError(shouldShowToast)
            }
        }
    }

    private fun showLoading(shouldShowLoading: Boolean) {
        if (!shouldShowLoading) {
            progressBar.isVisible = true
            errorMessage.isVisible = false
            newsRv.isVisible = false
        }
    }

    private fun showNews(newsEntity: NewsEntity) {
        // hiding views
        progressBar.isVisible = false
        errorMessage.isVisible = false
        newsRv.isVisible = true
        newsAdapter.updateNews(newsEntity.articles)
    }

    private fun showError(shouldShowToast: Boolean) {
        if (shouldShowToast) {
            Toast.makeText(context, R.string.sorry_error, Toast.LENGTH_SHORT).show()
        } else {
            progressBar.isVisible = false
            errorMessage.isVisible = true
            newsRv.isVisible = false
            retryButton.setOnClickListener {
                viewModel.getNews(newsAdapter.itemCount)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }
}
