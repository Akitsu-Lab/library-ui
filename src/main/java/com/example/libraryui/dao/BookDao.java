package com.example.libraryui.dao;

import com.example.libraryui.domain.BookList;

public  interface BookDao {
    BookList find(String bookTitle);
}
