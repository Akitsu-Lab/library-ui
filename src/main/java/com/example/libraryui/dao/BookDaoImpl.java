package com.example.libraryui.dao;

import com.example.libraryui.configuration.ApiCallConfigurationProperties;
import com.example.libraryui.domain.Book;
import com.example.libraryui.domain.BookList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookDaoImpl implements BookDao, InitializingBean {

    private final ApiCallConfigurationProperties properties;
    private final RestOperations restOperations;
    private String bookApiUrlPrefix;

    public BookDaoImpl(ApiCallConfigurationProperties properties, RestOperations restOperations) {
        this.properties = properties;
        this.restOperations = restOperations;
    }

    @Override
    public BookList find(String bookTitle) {
        // APIコールのURLを作成する
        // UriComponentsBuilderクラスを利用する
        // URLはbookApiUrlRefixを利用する
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.bookApiUrlPrefix);
        // クエリパラメータの処理
        if (bookTitle != null) {
            builder.queryParam("bookTitle", bookTitle);
        }
        //  APIコール
        // getForObjectメソッドを実行
        return restOperations.getForObject(builder.build().toUriString(), BookList.class);
    }

    @Override
    public Book get(long bookId) {
        String getApiUrl = this.bookApiUrlPrefix + "/{bookId}";
        Map<String, Long> params = new HashMap<>();
        params.put("bookId", bookId);
        return this.restOperations.getForObject(getApiUrl, Book.class, params);
    }

    @Override
    public void add(Book book) {
        String addApiUrl = this.bookApiUrlPrefix;

        // POSTリクエスト生成
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Book> request = new HttpEntity<>(book, headers);
        this.restOperations.postForObject(addApiUrl, request, String.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.bookApiUrlPrefix = "http://" + this.properties.getHost() + ":" + this.properties.getPort() + "/services/v1/books";
    }

}
