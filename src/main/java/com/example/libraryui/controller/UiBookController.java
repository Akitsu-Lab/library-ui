package com.example.libraryui.controller;

import com.example.libraryui.dao.BookDao;
import com.example.libraryui.domain.Book;
import com.example.libraryui.domain.BookList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("bookList", bookList);
        // ページタイトルをセット
        model.addAttribute("pageTitle", "本一覧");
        return "books/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        // Thymeleafのテンプレートに空のbookインスタンスを渡す
        // bookというキーで登録
        // validationのメッセージを出力するために必要
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("pageTitle", "本新規登録");
        return "books/add";
    }

    @PostMapping("add")
    String add(@Validated Book book,
               BindingResult result,
               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "本新規登録");
            return "books/add";
        }
        this.bookDao.add(book);
        return "redirect:list";
    }

}
