package com.example.libraryui.dao;

import com.example.libraryui.domain.BookList;
import org.springframework.beans.factory.InitializingBean;

public class BookDaoImpl implements BookDao, InitializingBean {

    @Override
    public BookList find(String bookTitle) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
