package com.ucb.data

import com.ucb.data.books.IBookLocalDataSource
import com.ucb.data.books.IBookRemoteDataSource
import com.ucb.domain.Book

class BookRepository(
    val remoteDataSource: IBookRemoteDataSource,
    val localDataSource: IBookLocalDataSource
) {
    suspend fun getBooks(query: String): NetworkResult<List<Book>> {
        return remoteDataSource.fetchBooks(query)
    }

    suspend fun saveBook(book: Book): Boolean {
        return localDataSource.saveBook(book)
    }

    suspend fun getLocalBooks(): List<Book> {
        return localDataSource.getSavedBooks()
    }
}
