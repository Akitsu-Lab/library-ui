package com.example.libraryui.dao;

import com.example.libraryui.configuration.ApiCallConfigurationProperties;
import com.example.libraryui.domain.BookList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

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
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(
                        this.bookApiUrlPrefix);
        if(bookTitle != null){
            builder.queryParam("bookTitle", bookTitle);
        }
        return restOperations.getForObject(
                builder.build().toUriString(),
                BookList.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.bookApiUrlPrefix = "http://" + this.properties.getHost() + ":"
                + this.properties.getPort() + "/services/v1/books";
    }
}
