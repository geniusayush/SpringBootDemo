import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.Article;
import com.sherlockcodes.libraryDemo.pojo.ArticleCreationInput;
import com.sherlockcodes.libraryDemo.pojo.ArticleUpdationInput;
import com.sherlockcodes.libraryDemo.repository.ArticleRepository;
import com.sherlockcodes.libraryDemo.service.ArticleService;
import com.sherlockcodes.libraryDemo.service.ArticleServiceImmpl;
import com.sherlockcodes.libraryDemo.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * while the repository test case checked for the creation fo the data this class will only check for
 * business logic. So only the a
 */
@RunWith(SpringRunner.class)
public class ArticleServiceTest {


    @Autowired
    private ArticleService articleService;
    @MockBean
    private ArticleRepository articleRepository;
    @MockBean
    private AuthorService authorService;

    @Test
    public void testDeleteNull() {
        try {
            articleService.deleteArticle(null);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testCreateNull() {
        try {
            articleService.createArticle(null);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testUpdateNull() {
        try {
            articleService.updateArticle(null, null);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testUpdateNotPresent() {
        Mockito.when(articleRepository.contains(Mockito.anyLong()))
                .thenReturn(false);
        try {
            articleService.updateArticle(1232l, null);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testCreateFailAuthorNotPresent() {
        ArticleCreationInput aci = createValidArticle();
        aci.setAuthor(new ArrayList<>());
        try {
            articleService.createArticle(aci);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testUpdateFailAuthorArrayIs0() {
        ArticleUpdationInput aci = createValidUpdateArticleInput();
        Mockito.when(articleRepository.contains(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(articleRepository.get(Mockito.anyLong()))
                .thenReturn(getArticle());
        aci.setAuthorToAdd(null);
        //
        try {
            articleService.updateArticle(getArticle().getId(), aci);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testUpdateFailNewAuthorsInvalid() {
        ArticleUpdationInput aci = createValidUpdateArticleInput();
        Mockito.when(articleRepository.contains(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(articleRepository.get(Mockito.anyLong()))
                .thenReturn(getArticle());
        Mockito.when(authorService.contains(Mockito.anyLong()))
                .thenReturn(false);
        //
        try {
            articleService.updateArticle(getArticle().getId(), aci);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testUpdateSuccess() throws LibraryException {
        ArticleUpdationInput aci = createValidUpdateArticleInput();
        Article art1 = getArticle();
        Mockito.when(articleRepository.contains(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(articleRepository.get(Mockito.anyLong()))
                .thenReturn(art1);
        Mockito.when(authorService.contains(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.when(articleRepository.update(Mockito.any(), Mockito.any()))
                .thenReturn(art1);
        Mockito.doNothing().when(authorService).linkArticle(Mockito.anyLong(), Mockito.anyLong());
        Mockito.doNothing().when(authorService).deLinkArticle(Mockito.anyLong(), Mockito.anyLong());

        try {
            Article art = articleService.updateArticle(getArticle().getId(), aci);
            assertEquals(art.getId(), getArticle().getId());
        } catch (LibraryException e) {
            fail();
        }
    }

    @Test
    public void testCreateFailDateNotPresent() {
        ArticleCreationInput aci = createValidArticle();
        aci.setPublishDate(null);
        try {
            articleService.createArticle(aci);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testCreateFailAuthorNotInSystem() {
        ArticleCreationInput aci = createValidArticle();
        Mockito.when(authorService.contains(Mockito.anyLong()))
                .thenReturn(false);
        try {
            articleService.createArticle(aci);
            fail();

        } catch (LibraryException e) {
            return;
        }
    }

    @Test
    public void testCreateSuccess() throws LibraryException {
        ArticleCreationInput aci = createValidArticle();
        Mockito.when(authorService.contains(Mockito.anyLong()))
                .thenReturn(true);
        Mockito.doNothing().when(authorService).linkArticle(Mockito.anyLong(), Mockito.anyLong());

        Mockito.when(articleRepository.create(aci))
                .thenReturn(getArticle());
        try {
            Article ar = articleService.createArticle(aci);
            assertTrue(ar.getId() == getArticle().getId());

        } catch (LibraryException e) {
            return;
        }
    }

    /*   @Test
    Unable to figure out how to check these values
       public void getTimeStampNullValues() {
           ArgumentCaptor<Long> argument = ArgumentCaptor.forClass(Long.class);
           Mockito.verify(articleService).get(argument.capture(), Mockito.anyLong());
           assertEquals(0l,argument.getValue().longValue());

       }
   */
    private Article getArticle() {
        ArrayList<Long> user;
        user = new ArrayList<>();
        user.add(12L);
        return new Article.ArticleBuilder(1l, 0l, user).build();
    }

    private ArticleCreationInput createValidArticle() {
        ArticleCreationInput aci = new ArticleCreationInput();
        ArrayList user;
        user = new ArrayList<Long>();
        user.add(12l);
        aci.setAuthor(user);
        aci.setDesc("desc1");
        aci.setPublishDate(123l);
        aci.setHeader("segt");
        ArrayList<String> keyword = new ArrayList<>();
        keyword.add("key");
        aci.setKeyword(keyword);
        return aci;
    }

    private ArticleUpdationInput createValidUpdateArticleInput() {
        ArticleUpdationInput aci = new ArticleUpdationInput();
        ArrayList<Long> user;
        user = new ArrayList<>();
        user.add(123l);

        aci.setAuthorToAdd(user.toArray(new Long[0]));
        ArrayList<Long> user2;
        user2 = new ArrayList<Long>();
        user2.add(12l);
        aci.setAuthorToRemove(user2.toArray(new Long[0]));
        aci.setDesc("desc1");
        aci.setPublishDate(123l);
        aci.setHeader("segt");
        ArrayList<String> keyword = new ArrayList<String>();
        keyword.add("key");
        aci.setKeywordtoAdd(keyword);
        ArrayList<String> keyword2 = new ArrayList<String>();
        keyword.add("not");
        aci.setKeywordtoRemove(keyword2);
        return aci;
    }

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ArticleService articleService() {
            return new ArticleServiceImmpl();
        }
    }


}