package com.example.libraryui.controller;

import com.example.libraryui.dao.BookDao;
import com.example.libraryui.domain.Book;
import com.example.libraryui.domain.BookList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("edit")
    public String edit(@RequestParam long bookId, Model model) {
        Book book = this.bookDao.get(bookId);
        model.addAttribute("book", book);
        model.addAttribute("title", "本更新");
        return "books/edit";
    }

    @PostMapping("edit")
    public String edit(@Validated Book book,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "本更新");
            return "books/edit";
        }
        this.bookDao.set(book);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String delete(@RequestParam long bookId) {
        this.bookDao.remove(bookId);
        return "redirect:list";
    }

}
