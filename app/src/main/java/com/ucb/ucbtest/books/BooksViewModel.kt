package com.ucb.ucbtest.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.Book
import com.ucb.usecases.GetBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooks: GetBooks
): ViewModel() {
    sealed class BooksState {
        object Idle: BooksState()
        object Loading: BooksState()
        data class Success(val books: List<Book>): BooksState()
        data class Error(val message: String): BooksState()
    }
    //mutables and observables
    private val _state = MutableStateFlow<BooksState>(BooksState.Idle)
    val state: StateFlow<BooksState> = _state

    fun searchBooks(query: String) {
        _state.value = BooksState.Loading
        viewModelScope.launch {
            _state.value = BooksState.Loading
            try {
                when (val result = getBooks(query)) {
                    is NetworkResult.Success -> {
                        _state.value = BooksState.Success(result.data)
                    }
                    is NetworkResult.Error -> {
                        _state.value = BooksState.Error(result.error)
                    }
                }
            } catch (e: Exception) {
                _state.value = BooksState.Error(e.message ?: "Error desconocido")
            }
        }

    }
}