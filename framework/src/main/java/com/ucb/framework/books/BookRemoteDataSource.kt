package com.ucb.framework.books

import com.ucb.data.NetworkResult
import com.ucb.data.books.IBookRemoteDataSource
import com.ucb.domain.Book
import com.ucb.framework.mappers.toModelList
import com.ucb.framework.service.RetrofitBuilder

class BookRemoteDataSource(
    private val retrofitBuilder: RetrofitBuilder
) : IBookRemoteDataSource {
    override suspend fun fetchBooks(query: String): NetworkResult<List<Book>> {
        val response = retrofitBuilder.bookService.fetchBooks(query)
        return if (response.isSuccessful) {
            NetworkResult.Success(response.body()?.toModelList() ?: emptyList())
        } else {
            NetworkResult.Error(response.message())
        }
    }
}
