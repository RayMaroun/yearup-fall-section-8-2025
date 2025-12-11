package com.pluralsight.apireview.dao.interfaces;

import com.pluralsight.apireview.models.Book;

import java.util.List;

public interface IBookDAO {

    List<Book> getAllBooks(String title, String author, Integer publicationYear);

    Book getBookById(Integer id);

    Book insertBook(Book book);

    void updateBook(Integer id, Book book);

    void deleteBook(Integer id);
}
