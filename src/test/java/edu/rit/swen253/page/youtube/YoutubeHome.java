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

public class YouTubeHome extends AbstractPage {

    private static final Logger LOGG = Logger.getLogger(YouTubeHome.class.getName());
    private static final By MAIN_CONTENT_FINDER = By
            .cssSelector("div#content.style-scope.ytd-app");

    private DomElement mainContent;

    public YouTubeHome() {
        super();

        try {
            mainContent = findOnPage(MAIN_CONTENT_FINDER);
        } catch (TimeoutException e) {
            fail("Main content panel not found");
        }
    }

    public void performSearch(String query) {
        DomElement searchBoxElement = mainContent.findChildBy(By.tagName("ytd-searchbox"));
        DomElement searchBox = searchBoxElement.findChildBy(By.cssSelector("input#search"));

        SeleniumUtils.getLongWait().until(ExpectedConditions.elementToBeClickable(searchBox.getWebElement()));

        searchBox.clear();
        searchBox.enterText(query);
        LOGG.info("Searching for: " + query);

        DomElement searchButton = searchBoxElement.findChildBy(By.cssSelector("button#search-icon-legacy"));
        searchButton.click();
        LOGG.info("Search button clicked");
    }

    public List<YoutubeSearchResults> getSearchResults() {
        DomElement searchResults = mainContent.findChildBy(By.cssSelector("ytd-search"));
        return searchResults.findChildrenBy(By.cssSelector("ytd-video-renderer"))
                .stream()
                .map(YoutubeSearchResults::new)
                .toList();
    }
}
