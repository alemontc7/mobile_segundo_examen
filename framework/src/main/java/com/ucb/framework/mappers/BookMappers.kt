package com.ucb.framework.mappers

import com.ucb.domain.Book
import com.ucb.framework.dto.BookDto
import com.ucb.framework.dto.BookResponseDto

fun BookDto.toModel(): Book {
    return Book(
        title = title,
        authors = authors,
        publicationYear = publicationYear
    )
}

fun BookResponseDto.toModelList(): List<Book> {
    return docs.map { it.toModel() }
}
