package com.ismin.android

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BookshelfUnitTest {
    private val theLordOfTheRings = Book(
        title = "The Lord of the Rings",
        author = "J. R. R. Tolkien",
        date = LocalDate.parse("1954-02-15")
    )

    private val theHobbit = Book(
        title = "The Hobbit",
        author = "J. R. R. Tolkien",
        date = LocalDate.parse("1937-09-21")
    )
    private val aLaRechercheDuTempsPerdu = Book(
        title = "À la recherche du temps perdu",
        author = "Marcel Proust",
        date = LocalDate.parse("1927-01-01")
    );

    private lateinit var bookshelf: Bookshelf

    @Before
    fun setup() {
        bookshelf = Bookshelf()
    }

    @Test
    fun getBook_returns_stored_book() {
        bookshelf.addBook(theLordOfTheRings);

        assertEquals(theLordOfTheRings, bookshelf.getBook("The Lord of the Rings"))
    }

    @Test
    fun getAllBooks_returns_all_stored_books_ordered_by_title() {
        bookshelf.addBook(theLordOfTheRings);
        bookshelf.addBook(theHobbit);
        bookshelf.addBook(aLaRechercheDuTempsPerdu);

        assertEquals(
            arrayListOf(aLaRechercheDuTempsPerdu, theHobbit, theLordOfTheRings),
            bookshelf.getAllBooks()
        )
    }

    @Test
    fun getBooksOf_returns_all_books_with_input_author() {
        bookshelf.addBook(theLordOfTheRings);
        bookshelf.addBook(theHobbit);
        bookshelf.addBook(aLaRechercheDuTempsPerdu);

        assertEquals(
            arrayListOf(theHobbit, theLordOfTheRings),
            bookshelf.getBooksOf("J. R. R. Tolkien")
        )
    }

    @Test
    fun getTotalNumberOfBooks_returns_number_of_stored_books() {
        bookshelf.addBook(theLordOfTheRings);
        bookshelf.addBook(theHobbit);
        bookshelf.addBook(aLaRechercheDuTempsPerdu);

        assertEquals(3, bookshelf.getTotalNumberOfBooks())
    }

    @Test
    fun should_not_duplicate_a_book_already_stored() {
        bookshelf.addBook(theLordOfTheRings);
        assertEquals(1, bookshelf.getTotalNumberOfBooks())

        bookshelf.addBook(theLordOfTheRings);
        assertEquals(1, bookshelf.getTotalNumberOfBooks())
    }

    @Test
    fun getBooksPublishedBefore_returns_books_published_before_a_date() {
        bookshelf.addBook(theLordOfTheRings);
        bookshelf.addBook(theHobbit);
        bookshelf.addBook(aLaRechercheDuTempsPerdu);

        assertEquals(
            listOf(aLaRechercheDuTempsPerdu, theHobbit), bookshelf.getBooksPublishedBefore(
                LocalDate.parse("1938-01-01")
            )
        )
    }
}