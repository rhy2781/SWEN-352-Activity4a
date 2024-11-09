package edu.rit.swen253.test.youtube;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.youtube.YoutubeHome;
import edu.rit.swen253.page.youtube.YoutubeSearchResults;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.SeleniumUtils;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YoutubeSearchResultsTest extends AbstractWebTest {

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
     * Perform a search and verify that results are returned
     */
    @Test
    @Order(2)
    public void testPerformSearchResults() {
        homePage.performSearch("first youtube video");

        List<YoutubeSearchResults> searchResults = homePage.getSearchResults();
        assertTrue(searchResults.size() > 0, "No search results found");

        firstSearchResults = searchResults.get(0);
        assertNotNull(firstSearchResults, "Run test again, firstSearchResult is null");
    }

    /**
     * Test to verify that the first result of search is youtubes first video
     */
    @Test
    @Order(3)
    public void testFirstResult() {
        String title = firstSearchResults.getTitle();
        assertEquals("Me at the zoo", title);
    }

    /**
     * Click the first result of the search and verify the video page title
     */
    @Test
    @Order(4)
    public void testClickFirstResult() {
        firstSearchResults.click();

        String expectedTitle = "Me at the zoo - YouTube";
        SeleniumUtils.getShortWait().until(
                driver -> driver.getTitle().contains(expectedTitle));

        String actualTitle = SeleniumUtils.getDriver().getTitle();
        assertTrue(expectedTitle.equals(actualTitle));
    }
}
