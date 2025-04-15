package com.ucb.data

import com.ucb.data.books.IBookRemoteDataSource
import com.ucb.domain.Book

class BookRepository(
    val remoteDataSource: IBookRemoteDataSource
) {
    suspend fun getBooks(query: String): NetworkResult<List<Book>> {
        return remoteDataSource.fetchBooks(query)
    }
}
