package com.ismin.android

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.Normalizer
import java.time.LocalDate

class Bookshelf {
    private var books: MutableMap<String, Book> = mutableMapOf()

    fun addBook(book: Book): Boolean {
        if (books.containsKey(book.title))
            return false
        books[book.title] = book
        return true
    }

    fun getBook(title: String): Book? {
        return books[title]
    }

    fun getBooksOf(author: String): List<Book> {
        return books.filterValues { it.author == author }.values.toList().sortedWith(compareBy({
            Normalizer.normalize(
                it.title,
                Normalizer.Form.NFD
            )
        }, { it.author }, { it.date }))
    }

    fun getAllBooks(): List<Book> {
        return books.values.toList().sortedWith(compareBy({
            Normalizer.normalize(
                it.title,
                Normalizer.Form.NFD
            )
        }, { it.author }, { it.date }))
    }


    fun getTotalNumberOfBooks(): Int {
        return books.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getBooksPublishedBefore(date: LocalDate): List<Book> {
        return books.filterValues { it.date < date }.values.toList().sortedWith(
            compareBy({
                Normalizer.normalize(
                    it.title,
                    Normalizer.Form.NFD
                )
            }, { it.author }, { it.date })
        )
    }
}