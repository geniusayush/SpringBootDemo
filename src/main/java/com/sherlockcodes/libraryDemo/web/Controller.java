package com.sherlockcodes.libraryDemo.web;

import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.*;
import com.sherlockcodes.libraryDemo.service.ArticleService;
import com.sherlockcodes.libraryDemo.service.AuthorService;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
@Api(value = "article enter", description = "basic controller that lets users create and edit articles")
/**
 *
 * */
public class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class);
    @Autowired
    ArticleService moduleService;
    @Autowired
    AuthorService authorService;
    @Autowired
    ArticleResponseFactory articleResponseFactory;

    @ApiOperation(value = "create an author", response = Author.class)
    @RequestMapping(value = "/author", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server Exception")
    })
    public Author createAuthor(@ApiParam(value = "name of author", required = true) @RequestParam String name) throws LibraryException {
        logger.trace("entered createAuthor");
        return authorService.createAuthor(name);
    }

    @ApiOperation(value = "create an article", notes = "authors should be pre-existing. create the authors first", response = ArticleDTO.class)
    @RequestMapping(value = "/articles", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server Exception")
    })
    public ArticleDTO createArticle(@ApiParam(value = "details of article", required = true) @RequestBody ArticleCreationInput input) throws LibraryException {
        logger.trace("entered createAuthor");
        return articleResponseFactory.getResponse(moduleService.createArticle(input));

    }

    @ApiOperation(value = "delete an article")
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server Exception")
    })
    public void deleteArticle(@ApiParam(value = "id of article", required = true) @PathVariable Long id) throws LibraryException {

        moduleService.deleteArticle(id);

    }

    @ApiOperation(value = "update an article", notes = "the api takes in for authors and tags,spereate list for those to be added and those to be deleted", response = Article.class)
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server Exception")
    })
    public ArticleDTO upadteArticle(@ApiParam(value = "id of article", required = true) @PathVariable Long id, @ApiParam(value = "changes " +
            "to be done on inpur", required = true) @RequestBody ArticleUpdationInput input) throws LibraryException {

        return articleResponseFactory.getResponse(moduleService.updateArticle(id, input));

    }

    @ApiOperation(value = "get an article", response = ArticleDTO.class)
    @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server Exception")
    })
    public ArticleDTO getArticle(@ApiParam(value = "id of article", required = true)
                                 @PathVariable Long id) throws LibraryException {

        return articleResponseFactory.getResponse(moduleService.getArticle(id));

    }

    @ApiOperation(value = "list  articles", response = ArticleDTO.class)
    @RequestMapping(value = "/articles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Server Exception")
    })
    public ArticleDTO[] getArticleasListByKeyword(@ApiParam(value = "filter article")
                                                  @RequestParam(value = "keyword", required = false) String keyword,
                                                  @ApiParam(value = "filter article by author")
                                                  @RequestParam(value = "author", required = false) Long author,
                                                  @ApiParam(value = "start date")
                                                  @RequestParam(value = "start", required = false) Long start,
                                                  @ApiParam(value = "end date")
                                                  @RequestParam(value = "end", required = false) Long end

    ) throws LibraryException {

        if (keyword != null)
            return articleResponseFactory.getResponse(moduleService.getByKeyword(keyword));

        if (author != null)
            return articleResponseFactory.getResponse(moduleService.getByAuthor(author));
        if (start != null || end != null)
            return articleResponseFactory.getResponse(moduleService.getBytimeStamp(start, end));
        return articleResponseFactory.getResponse(moduleService.getAll());

    }

}



