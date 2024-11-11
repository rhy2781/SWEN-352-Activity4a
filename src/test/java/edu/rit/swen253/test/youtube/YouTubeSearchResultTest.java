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
import edu.rit.swen253.page.youtube.YouTubeVideoPage;
import edu.rit.swen253.test.AbstractWebTest;

/**
 * Tests for YouTube search results functionality.
 * 
 * @author Austyn Wright
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YouTubeSearchResultTest extends AbstractWebTest {

    private static final Logger LOGG = Logger.getLogger(YouTubeSearchResultTest.class.getName());

    private YouTubeHomePage homePage;
    private YouTubeSearchResult firstResult;

    /**
     * Navigate to the YouTube home page
     */
    @Test
    @Order(1)
    public void navigateToHomePage() {
        homePage = navigateToPage("https://www.youtube.com/", YouTubeHomePage::new);
    }

    /**
     * Performs a search and verifies that results are returned.
     */
    @Test
    @Order(2)
    public void testPerformSearchResults() {
        // Perform a search for "first youtube video"
        homePage.performSearch("first youtube video");

        // Retrieve the search results
        List<YouTubeSearchResult> searchResults = homePage.getSearchResults();

        // Store the first search result
        firstResult = searchResults.get(0);

        LOGG.info("First result Title: " + firstResult.getTitle());
        LOGG.info("First result URL: " + firstResult.getUrl());
    }

    /**
     * Tests to verify that the first result of the search is YouTube's first video.
     */
    @Test
    @Order(3)
    public void testFirstResult() {
        // Ensure the first result is not null
        assertNotNull(firstResult, "First search result is null");

        String title = firstResult.getTitle();
        assertEquals("Me at the zoo", title, "First result title does not match expected title");
    }

    /**
     * Clicks the first result of the search and verifies the video page title.
     */
    @Test
    @Order(4)
    public void testClickFirstResult() {
        // Ensure the first result is not null
        assertNotNull(firstResult, "First search result is null");

        firstResult.click();

        YouTubeVideoPage videoPage = new YouTubeVideoPage();
        String actualTitle = videoPage.getTitle();

        assertEquals("Me at the zoo - YouTube", actualTitle, "Page title after clicking does not match expected title");
    }
}
