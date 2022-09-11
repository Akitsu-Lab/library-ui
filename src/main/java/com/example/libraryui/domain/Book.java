package com.example.libraryui.domain;

import javax.validation.constraints.NotEmpty;

public class Book {

    private long bookId;
    @NotEmpty(message = "タイトルを空文字にできません")
    private String bookTitle;
    //    @NotEmpty(message = "ページ数は空文字にできません")
    private int bookPages;
    private String bookContent;

    public Book() {
    }

    public Book(long bookId, String bookTitle, int bookPages, String bookContent) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookPages = bookPages;
        this.bookContent = bookContent;
    }

    //getterとsetter
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getBookPages() {
        return bookPages;
    }

    public void setBookPages(int bookPages) {
        this.bookPages = bookPages;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }
}
