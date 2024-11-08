package edu.rit.swen253.test.youtube;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.youtube.YoutubeHome;
import edu.rit.swen253.page.youtube.YoutubeSearchResults;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YoutubeSearchResultsTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(YoutubeSearchResultsTest.class.getName());

    private YoutubeHome homePage;
    private YoutubeSearchResults firstSearchResults;

    /**
     * Navigate to the youtube home page
     */
    @Test
    @Order(1)
    public void navigateToHomePage() {
        homePage = navigateToPage("https://www.youtube.com/", YoutubeHome::new);
    }

    /**
     * Get collection of search results
     */
    @Test
    @Order(2)
    public void performSearchResults() {
        homePage.performSearch("first youtube video");
    }

    /**
     * Test to make sure that search results are found
     */
    @Test
    @Order(3)
    public void testSearchResults() {
        List<YoutubeSearchResults> searchResults = homePage.getSearchResults();
        assertTrue(searchResults.size() > 0, "No search results found");

        firstSearchResults = searchResults.get(0);
    }

    /**
     * Test to make sure that the first result of search is youtubes first video
     */
    @Test
    @Order(4)
    public void testFirstResult() {
        String title = firstSearchResults.getTitle();
        String url = firstSearchResults.getUrl();

        assertAll(
            () -> assertEquals("Me at the zoo", title),
            () -> assertTrue(url.contains("watch"))
        );
    }
}
