package edu.rit.swen253.test.youtube;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.youtube.YouTubeHomePage;
import edu.rit.swen253.page.youtube.YouTubeSearchResult;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.SeleniumUtils;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YouTubeSearchResultTest extends AbstractWebTest {

    private static final Logger LOGG = Logger.getLogger(YouTubeHomePage.class.getName());

    private YouTubeHomePage homePage;
    private YouTubeSearchResult firstResult;

    /**
     * Navigate to the youtube home page
     */
    @Test
    @Order(1)
    public void navigateToHomePage() {
        homePage = navigateToPage("https://www.youtube.com/", YouTubeHomePage::new);
    }

    /**
     * Perform a search and verify that results are returned
     */
    @Test
    @Order(2)
    public void testPerformSearchResults() {
        homePage.performSearch("first youtube video");

        List<YouTubeSearchResult> searchResults = homePage.getSearchResults();
        assertTrue(searchResults.size() > 0, "No search results found");

        firstResult = searchResults.get(0);

        LOGG.info("-----------------------------");
        LOGG.info("First result Title: " + firstResult.getTitle());
        LOGG.info("First result URL: " + firstResult.getUrl());
        LOGG.info("-----------------------------");
    }

    /**
     * Test to verify that the first result of search is youtubes first video
     */
    @Test
    @Order(3)
    public void testFirstResult() {
        String title = firstResult.getTitle();
        assertEquals("Me at the zoo", title, "First result title does not match expected title");
    }

    /**
     * Click the first result of the search and verify the video page title
     */
    @Test
    @Order(4)
    public void testClickFirstResult() {
        firstResult.click();

        String expectedTitle = "Me at the zoo - YouTube";
        SeleniumUtils.getShortWait().until(
                driver -> driver.getTitle().contains(expectedTitle));

        String actualTitle = SeleniumUtils.getDriver().getTitle();
        assertTrue(expectedTitle.equals(actualTitle), "Page title after clicking does not match expected title");
    }
}