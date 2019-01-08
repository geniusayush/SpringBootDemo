package com.sherlockcodes.libraryDemo.web;

import com.sherlockcodes.libraryDemo.pojo.Article;
import com.sherlockcodes.libraryDemo.pojo.ArticleDTO;
import com.sherlockcodes.libraryDemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class ArticleResponseFactory {

    @Autowired
    AuthorService authorService;


    public ArticleDTO getResponse(Article article) {

        ArticleDTO dto = new ArticleDTO();
        dto.setPublishDate(new Date(article.getPublishDate()));
        dto.setText(article.getText());
        dto.setDesc(article.getDesc());
        dto.setId(article.getId());
        dto.setKeyword(article.getKeyword());
        dto.setHeader(article.getHeader());
        dto.setAuthors(new ArrayList<>());
        for (Long id : article.getAuthors()) {
            dto.getAuthors().add(authorService.getAuthor(id).getName());
        }
        return dto;

    }

    public ArticleDTO[] getResponse(Article[] articleList) {
        ArticleDTO[] arr = new ArticleDTO[articleList.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getResponse(articleList[i]);
        }
        return arr;
    }
}
