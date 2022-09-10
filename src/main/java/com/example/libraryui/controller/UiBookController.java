package com.example.libraryui.controller;

import com.example.libraryui.dao.BookDao;
import com.example.libraryui.domain.BookList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("ui/books")
public class UiBookController {
    private final BookDao bookDao;

    public UiBookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("list")
    public String list(@RequestParam(required = false) String bookTitle, Model model) {
        BookList bookList = this.bookDao.find(bookTitle);
        // Thymeleafのテンプレートに渡すデータをbookListというキーで登録
        model.addAttribute("bookList",bookList);
        // ページタイトルをセット
        model.addAttribute("pageTitle","本一覧");
        return "books/list";
    }
}
