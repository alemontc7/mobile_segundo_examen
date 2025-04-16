package com.ucb.ucbtest.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucb.domain.Book

@Composable
fun BooksUI(booksViewModel: BooksViewModel = hiltViewModel()) {
    val searchQuery = remember { mutableStateOf("") }
    val uiState = booksViewModel.state.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Spacer(modifier = Modifier.height(48.dp))

        TextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Buscar libro") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { booksViewModel.searchBooks(searchQuery.value) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is BooksViewModel.BooksState.Loading -> {
                Box(

                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is BooksViewModel.BooksState.Success -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(uiState.books) { book ->
                        BookCard(
                            book = book,
                            onFavoriteClick = { booksViewModel.onLikeBook(book) }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            is BooksViewModel.BooksState.Error -> {
                Text(
                    text = uiState.message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            else -> {
                Text(
                    text = "Ingresa un libro para buscr",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun BookCard(
    book: Book,
    onFavoriteClick: (Book) -> Unit = {}
) {
    val isFavorite = remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        isFavorite.value = !isFavorite.value
                        onFavoriteClick(book)
                    }
                ) {
                    Icon(
                        imageVector = if (isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite.value) "Quitar de favoritos" else "Añadir a favoritos",
                        tint = if (isFavorite.value) Color.Red else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
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
