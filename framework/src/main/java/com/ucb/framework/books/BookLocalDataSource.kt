package com.ucb.framework.books

import android.content.Context
import com.ucb.data.books.IBookLocalDataSource
import com.ucb.domain.Book
import com.ucb.framework.mappers.toEntity
import com.ucb.framework.mappers.toModel
import com.ucb.framework.persistence.AppBookRoomDatabase
import com.ucb.framework.persistence.IBookDAO

class BookLocalDataSource(val context: Context) : IBookLocalDataSource {
    private val bookDAO: IBookDAO = AppBookRoomDatabase.getDatabase(context).bookDao()
    override suspend fun getSavedBooks(): List<Book> {
        return bookDAO.getBooks().map { it.toModel() }
    }

    override suspend fun saveBook(book: Book): Boolean {
        bookDAO.insert(book.toEntity())
        return true
    }
}