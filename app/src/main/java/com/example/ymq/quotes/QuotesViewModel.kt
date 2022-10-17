package com.example.ymq.quotes

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ymq.R
import com.example.ymq.pagestate.QuotesScreenState
import com.example.ymq.remote.QuotesRepository
import com.example.ymq.remote.QuotesRepositoryImpl
import kotlinx.coroutines.flow.*

class QuotesViewModel(
    private val resources: Resources,
    private val quotesRepository: QuotesRepository = QuotesRepositoryImpl()
) : ViewModel() {

    val pageState: StateFlow<QuotesScreenState> =
        quotesRepository.contentStream.map { content ->
            if (content.quotesResponse.status == 200 && !content.quotesResponse.quotes.isNullOrEmpty()) {
                QuotesScreenState.Ready(content.quotesResponse.quotes[0], content.imageUrl)
            } else {
                QuotesScreenState.Error(content.quotesResponse.message)
            }
        }.catch { cause ->
            QuotesScreenState.Error(cause.message ?: resources.getString(R.string.error_generic))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = QuotesScreenState.Loading
        )

    class Factory(
        private val resources: Resources,
        private val repository: QuotesRepository = QuotesRepositoryImpl()
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            QuotesViewModel(resources, repository) as T
    }
}