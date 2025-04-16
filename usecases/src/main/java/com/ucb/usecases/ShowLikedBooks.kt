package com.ucb.usecases.books

import com.ucb.data.BookRepository
import com.ucb.domain.Book

class ShowLikedBooks(private val repository: BookRepository) {
    suspend operator fun invoke(): List<Book> {
        return repository.getLocalBooks()
    }
}
