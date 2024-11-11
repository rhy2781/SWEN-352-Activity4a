package edu.rit.swen253.page.youtube;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

/**
 * Respresents the YouTube Home Page and provides methods to interact with it.
 * 
 * @author Austyn Wright
 */
public class YouTubeHomePage extends AbstractPage {

    private static final Logger LOGG = Logger.getLogger(YouTubeHomePage.class.getName());
    private static final By MAIN_CONTENT_FINDER = By
            .cssSelector("div#content.style-scope.ytd-app");

    private DomElement mainContent;

    /**
     * Constructs a new YouTubeHomePage object and initializes the main content.
     */
    public YouTubeHomePage() {
        super();

        try {
            mainContent = findOnPage(MAIN_CONTENT_FINDER);
        } catch (TimeoutException e) {
            fail("Main content panel not found");
        }
    }

    /**
     * Performs a search on YouTube with the given query.
     * 
     * @param query The search query to be entered into the search box.
     */
    public void performSearch(String query) {
        DomElement searchBoxElement = mainContent.findChildBy(By.tagName("ytd-searchbox"));

        // Wait for the search box to be visible
        SeleniumUtils.getLongWait().until(ExpectedConditions.visibilityOf(searchBoxElement.getWebElement()));

        DomElement searchBox = searchBoxElement.findChildBy(By.cssSelector("input#search"));

        // Wait for the search box to be clickable
        SeleniumUtils.getLongWait().until(ExpectedConditions.elementToBeClickable(searchBoxElement.findChildBy(By.cssSelector("input#search")).getWebElement()));

        // Clear any existing text and enter the new query
        searchBox.clear();
        searchBox.enterText(query);
        LOGG.info("Searching for: " + query);

        // Click the search button to perform the search
        DomElement searchButton = searchBoxElement.findChildBy(By.cssSelector("button#search-icon-legacy"));
        searchButton.click();
        LOGG.info("Search button clicked");
    }

    /**
     * Retrives a list of videos from the search that was performed.
     * 
     * @return A list of {@link YouTubeSearchResult} objects representing the search
     *         results.
     */
    public List<YouTubeSearchResult> getSearchResults() {
        DomElement searchResults = mainContent.findChildBy(By.cssSelector("ytd-search"));
        return searchResults.findChildrenBy(By.cssSelector("ytd-video-renderer"))
                .stream()
                .map(YouTubeSearchResult::new)
                .toList();
    }
}
