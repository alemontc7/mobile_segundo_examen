package com.ucb.framework.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "authors")
    var authors: String = "",

    @ColumnInfo(name = "publication_year")
    var publicationYear: Int = 0,

    @ColumnInfo(name = "saved_date")
    var saved_date: String = ""
)