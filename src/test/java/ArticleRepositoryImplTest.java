import com.sherlockcodes.libraryDemo.common.LibraryException;
import com.sherlockcodes.libraryDemo.pojo.Article;
import com.sherlockcodes.libraryDemo.pojo.ArticleCreationInput;
import com.sherlockcodes.libraryDemo.repository.ArticleRepositoryHashMapImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ArticleRepositoryImplTest {

    private ArticleRepositoryHashMapImpl repo;

    @Before
    public void setUp() {
        repo = new ArticleRepositoryHashMapImpl();
    }

    @Test
    public void testContainsNull() throws Exception {
        assertFalse(repo.contains(null));
    }

    @Test
    public void testContainsFalse() throws Exception {
        assertFalse(repo.contains(123l));
    }

    @Test
    public void testCcreationNUllAuthor() throws Exception {
        ArticleCreationInput aci = new ArticleCreationInput();
        aci.setAuthor(null);


        try {
            repo.create(aci);
            fail();
        } catch (LibraryException e) {
            return;
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCcreationValid() throws Exception {
        ArticleCreationInput aci = createValidArticle();
        try {
            Article art = repo.create(aci);
            assertEquals(art.getAuthors().size(), 1);
            assert (art.getAuthors().get(0) == 12l);
            System.out.println(art.getDesc());
            System.out.println(aci.getDesc());
            assertEquals (art.getDesc(),(aci.getDesc()));
            assertEquals (art.getPublishDate(),aci.getPublishDate());
            assertEquals (art.getHeader(),aci.getHeader());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testContainsTrue() throws Exception {
        ArticleCreationInput aci = createValidArticle();
        try {
            Article art = repo.create(aci);
            assertTrue(repo.contains(1l));

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testContainsFalse2() throws Exception {
        ArticleCreationInput aci = createValidArticle();
        try {
            Article art = repo.create(aci);

            assertFalse(repo.contains(2l));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    /**
     * Testing here wheter we get the same response back as when we created the entity
     * **/
    public void testGet() throws Exception {
        ArticleCreationInput aci = createValidArticle();
        try {
            Article art = repo.create(aci);
            Article art2 = repo.get(art.getId());
            assertEquals(art2.getAuthors().size(), 1);
            assert (art.getAuthors().get(0) == art2.getAuthors().get(0));
            assert (art.getDesc().equals(art2.getDesc()));
            assert (art.getPublishDate() == art2.getPublishDate());
            assert (art.getHeader().equals(art2.getHeader()));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    /**
     * Testing the filters here
     * **/
    public void testFiltersByKeyword() throws Exception {
        ArticleCreationInput aci = createValidArticle();
        try {
            Article art = repo.create(aci);
            assert (repo.getByKeyword("key").length == 1);
            assert (repo.getByKeyword("notvalidkey").length == 0);

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    /**
     * Testing the updates here
     * **/
    public void testUpdateNull() throws Exception {
        ArticleCreationInput aci = createValidArticle();
        try {
            Article art = repo.update(null,null);
          fail();
        } catch (Exception e) {
            return;
        }
    }
    @Test
    /**
     * Testing the filters here
     * **/
    public void testFiltersByLength() throws Exception {
        ArticleCreationInput aci = createValidArticle();
        try {
            Article art = repo.create(aci);
            assert (repo.getByTime(0l, 444l).length == 1);
            assert (repo.getByTime(200l, 444l).length == 0);
            assert (repo.getByTime(0l, 100l).length == 0);
        } catch (Exception e) {
            fail();
        }
    }
@Test
    public void testFiltersByKeywordNull() throws Exception {
        ArticleCreationInput aci = createValidArticle();
        try {
            Article art = repo.create(aci);
            repo.getByKeyword(null);
            fail();

        } catch (Exception e) {

        }
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
        ArrayList<String> keyword = new ArrayList<String>();
        keyword.add("key");
        aci.setKeyword(keyword);
        return aci;
    }


}