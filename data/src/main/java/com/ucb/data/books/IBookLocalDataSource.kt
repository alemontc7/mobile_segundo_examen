package com.ucb.data.books

import com.ucb.domain.Book

interface IBookLocalDataSource {
    suspend fun getBooks(): List<Book>
    suspend fun saveBook(book: Book):Boolean
}