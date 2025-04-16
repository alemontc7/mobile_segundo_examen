package com.ucb.ucbtest.books

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucb.domain.Book

@Composable
fun SavedBooksUI(savedBooksViewModel: SavedBooksViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        savedBooksViewModel.loadSavedBooks()
    }

    val uiState = savedBooksViewModel.state.collectAsState().value

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            is SavedBooksViewModel.SavedBooksState.Loading -> {
                CircularProgressIndicator()
            }
            is SavedBooksViewModel.SavedBooksState.Success -> {
                val books = uiState.books
                if (books.isEmpty()) {
                    Text(
                        text = "No tenes libros guardadoss",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(books) { book ->
                            SavedBookCard(book = book)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
            is SavedBooksViewModel.SavedBooksState.Error -> {
                Text(
                    text = "Error: ${uiState.message}",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center
                )
            }
            else -> {
                Text(
                    text = "Cargando...",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun SavedBookCard(book: Book) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Autor(es): ${book.authors.joinToString(", ")}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Año de publicación: ${book.publicationYear}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
