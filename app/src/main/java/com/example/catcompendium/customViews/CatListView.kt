package com.example.catcompendium.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.VisibleForTesting
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catcompendium.databinding.LayoutCustomRecyclerViewBinding
import com.example.catcompendium.model.CatBreedItem
import com.example.catcompendium.ui.CatListAdapter
import com.example.catcompendium.ui.CatLoadStateAdapter

class CatListView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {
    private var adapter: CatListAdapter
    @VisibleForTesting var binding: LayoutCustomRecyclerViewBinding

    init {
        binding =
            LayoutCustomRecyclerViewBinding.inflate(LayoutInflater.from(context), this, true)
        adapter = CatListAdapter()
        binding.apply {
            val rv = catList
            rv.adapter = adapter.withLoadStateHeaderAndFooter(
                header = CatLoadStateAdapter { adapter.retry() },
                footer = CatLoadStateAdapter { adapter.retry() }
            )
            rv.layoutManager = LinearLayoutManager(context)
        }

        addSwipeToRefresh()
        subscribeLoadStates()
    }

    suspend fun setData(catList: PagingData<CatBreedItem>) {
        adapter.submitData(catList)
    }

    private fun subscribeLoadStates() {

        adapter.addLoadStateListener { loadState ->
            when (loadState.refresh) {
                is LoadState.Loading -> showProgressBar()
                is LoadState.NotLoading -> hideProgressBar()
                is LoadState.Error -> showError(loadState.refresh as? LoadState.Error)
                else -> {}
            }
        }
    }

    private fun addSwipeToRefresh() {
        binding.swipeRefresh.setOnRefreshListener(this::refreshAdapter)
    }

    private fun refreshAdapter() {
        adapter.refresh()
        binding.progressBar.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false

    }

    private fun showError(errorState: LoadState.Error?) {
        errorState?.let { err ->
            binding.apply {
                errorText.text = err.error.message
            }.also { it.errorText.visibility = View.VISIBLE }
        }
        hideProgressBar()
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.errorText.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}

