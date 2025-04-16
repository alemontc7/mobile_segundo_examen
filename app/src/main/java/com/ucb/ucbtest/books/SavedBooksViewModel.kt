package com.ucb.ucbtest.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.domain.Book
import com.ucb.usecases.books.ShowLikedBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedBooksViewModel @Inject constructor(
    private val showLikedBooks: ShowLikedBooks
) : ViewModel() {

    sealed class SavedBooksState {
        object Idle : SavedBooksState()
        object Loading : SavedBooksState()
        data class Success(val books: List<Book>) : SavedBooksState()
        data class Error(val message: String) : SavedBooksState()
    }


    private val _state = MutableStateFlow<SavedBooksState>(SavedBooksState.Idle)
    val state: StateFlow<SavedBooksState> = _state

    fun loadSavedBooks() {
        _state.value = SavedBooksState.Loading
        viewModelScope.launch {
            try {
                val books = showLikedBooks()
                _state.value = SavedBooksState.Success(books)
            } catch (e: Exception) {
                _state.value = SavedBooksState.Error(e.message ?: "Error desconocido")
            }
        }
    }
}
