package com.ucb.ucbtest.di

import android.content.Context
import com.ucb.data.BookRepository
import com.ucb.data.books.IBookLocalDataSource
import com.ucb.data.books.IBookRemoteDataSource
import com.ucb.framework.books.BookLocalDataSource
import com.ucb.framework.books.BookRemoteDataSource
import com.ucb.framework.service.RetrofitBuilder
import com.ucb.usecases.GetBooks
import com.ucb.usecases.books.LikeBook
import com.ucb.usecases.books.ShowLikedBooks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(@ApplicationContext context: Context): RetrofitBuilder {
        return RetrofitBuilder(context)
    }


    // ============================
    // Books Feature
    // ============================

    // Proveedor del data source remoto para Books
    @Provides
    @Singleton
    fun provideBookRemoteDataSource(retrofitBuilder: RetrofitBuilder): IBookRemoteDataSource {
        return BookRemoteDataSource(retrofitBuilder)
    }

    // Proveedor del Repository para Books
    @Provides
    @Singleton
    fun provideBookRepository(
        remoteDataSource: IBookRemoteDataSource,
        localDataSource: IBookLocalDataSource
    ): BookRepository {
        return BookRepository(remoteDataSource, localDataSource)
    }


    // Proveedor del Use Case para obtener libros (GetBooks)
    @Provides
    @Singleton
    fun provideGetBooks(bookRepository: BookRepository): GetBooks {
        return GetBooks(bookRepository)
    }

    @Provides
    @Singleton
    fun provideBookLocalDataSource(@ApplicationContext context: Context): IBookLocalDataSource {
        return BookLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideLikeBook(bookRepository: BookRepository): LikeBook {
        return LikeBook(bookRepository)
    }

    @Provides
    @Singleton
    fun provideShowLikedBooks(bookRepository: BookRepository): ShowLikedBooks {
        return ShowLikedBooks(bookRepository)
    }
}