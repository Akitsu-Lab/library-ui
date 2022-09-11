package com.example.libraryui.dao;

import com.example.libraryui.domain.Book;
import com.example.libraryui.domain.BookList;

public interface BookDao {
    BookList find(String bookTitle);

    Book get(long bookId);

    void add(Book book);

    void set(Book book);
}
