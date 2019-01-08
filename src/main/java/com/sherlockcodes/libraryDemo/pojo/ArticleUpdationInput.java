package com.sherlockcodes.libraryDemo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@JsonPropertyOrder(alphabetic = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleUpdationInput {

    String header;
    String desc;

    String text;

    @JsonProperty("author_add")
    Long[] authorToAdd;
    @JsonProperty("author_remove")
    Long[] authorToRemove;
    @JsonProperty("keyword_add")
    List<String> keywordtoAdd;
    @JsonProperty("keyword_remove")
    List<String> keywordtoRemove;
    @JsonProperty("publish_date")
    Long publishDate;

    public ArticleUpdationInput() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long[] getAuthorToAdd() {
        return authorToAdd;
    }

    public void setAuthorToAdd(Long[] authorToAdd) {
        this.authorToAdd = authorToAdd;
    }

    public Long[] getAuthorToRemove() {
        return authorToRemove;
    }

    public void setAuthorToRemove(Long[] authorToRemove) {
        this.authorToRemove = authorToRemove;
    }

    public List<String> getKeywordtoAdd() {
        return keywordtoAdd;
    }

    public void setKeywordtoAdd(List<String> keywordtoAdd) {
        this.keywordtoAdd = keywordtoAdd;
    }

    public List<String> getKeywordtoRemove() {
        return keywordtoRemove;
    }

    public void setKeywordtoRemove(List<String> keywordtoRemove) {
        this.keywordtoRemove = keywordtoRemove;
    }

    public Long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Long publishDate) {
        this.publishDate = publishDate;
    }
}
