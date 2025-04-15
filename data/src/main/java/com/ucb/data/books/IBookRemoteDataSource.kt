package com.ucb.data.books

import com.ucb.data.NetworkResult
import com.ucb.domain.Book

interface IBookRemoteDataSource {
    suspend fun fetchBooks(query: String): NetworkResult<List<Book>>
}
