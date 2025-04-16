package com.ucb.framework.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 2, exportSchema = false)
abstract class AppBookRoomDatabase: RoomDatabase() {
    abstract fun bookDao(): IBookDAO
    companion object {
        @Volatile
        var Instance: AppBookRoomDatabase? = null
        fun getDatabase(context: Context): AppBookRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, AppBookRoomDatabase::class.java, "books_database")
                    .fallbackToDestructiveMigration() //tuve que poner esto
                    //por que cambie el schema para que se muestren los libros
                    //en orden de la fecha de guardado
                    .build()
                    .also { Instance = it }
            }
        }
    }
}



