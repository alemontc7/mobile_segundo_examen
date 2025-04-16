package com.ucb.usecases.books

import com.ucb.data.BookRepository
import com.ucb.domain.Book

class LikeBook(private val repository: BookRepository) {
    suspend operator fun invoke(book: Book): Boolean {
        return repository.saveBook(book)
    }
}
