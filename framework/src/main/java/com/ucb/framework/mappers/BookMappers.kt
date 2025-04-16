package com.ucb.framework.mappers

import com.ucb.domain.Book
import com.ucb.framework.dto.BookDto
import com.ucb.framework.dto.BookResponseDto
import com.ucb.framework.persistence.BookEntity

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

// de book de dominio a book entity de bd
fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = 0,
        title = this.title,
        authors = this.authors.joinToString(separator = ", "),
        publicationYear = this.publicationYear,
        saved_date = System.currentTimeMillis().toString()
    )
}

//de book entity bd a book de dominio
fun BookEntity.toModel(): Book {
    return Book(
        title = this.title,
        authors = this.authors.split(",").map { it.trim() },
        publicationYear = this.publicationYear
    )
}