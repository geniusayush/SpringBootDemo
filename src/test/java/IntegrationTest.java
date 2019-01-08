import com.sherlockcodes.libraryDemo.App;
import com.sherlockcodes.libraryDemo.pojo.ArticleCreationInput;
import com.sherlockcodes.libraryDemo.pojo.ArticleDTO;
import com.sherlockcodes.libraryDemo.pojo.ArticleUpdationInput;
import com.sherlockcodes.libraryDemo.pojo.Author;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = App.class)

public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSanityBAsic() {
        String url = "/library/author?name={name}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "ayush");
        Author author = restTemplate.postForObject(url, null, Author.class, map);
        assert author.getName().equals("ayush");

        ArticleDTO dto = restTemplate.postForObject("/library/articles", createValidArticle(), ArticleDTO.class);
        assert (dto.getAuthors().get(0).equals("ayush"));
        dto = restTemplate.getForObject("/library/articles/1", ArticleDTO.class);
        assert (dto.getAuthors().get(0).equals("ayush"));

        map.clear();
        map.put("name", "ramesh");
        author = restTemplate.postForObject(url, null, Author.class, map);
        assert author.getName().equals("ramesh");
        dto = restTemplate.postForObject("/library/articles/1", createValidUpdateArticleInput(), ArticleDTO.class);
        assert author.getName().equals("ramesh");

    }

    private ArticleCreationInput createValidArticle() {
        ArticleCreationInput aci = new ArticleCreationInput();
        ArrayList user;
        user = new ArrayList<Long>();
        user.add(1l);
        aci.setAuthor(user);
        aci.setDesc("desc1");
        aci.setPublishDate(123l);
        aci.setHeader("segt");
        ArrayList<String> keyword = new ArrayList<String>();
        keyword.add("key");
        aci.setKeyword(keyword);
        return aci;
    }

    private ArticleUpdationInput createValidUpdateArticleInput() {
        ArticleUpdationInput aci = new ArticleUpdationInput();
        ArrayList<Long> user;
        user = new ArrayList<>();
        user.add(2l);

        aci.setAuthorToAdd(user.toArray(new Long[0]));
        ArrayList<Long> user2;
        user2 = new ArrayList<Long>();
        user2.add(1l);
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
}
