package edu.rit.swen253.page.youtube;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class YoutubeHome extends AbstractPage {

    private static final Logger LOGG = Logger.getLogger(YoutubeHome.class.getName());
    private static final By MAIN_CONTENT_FINDER = By
            .cssSelector("div#content.style-scope.ytd-app");

    private DomElement mainContent;

    public YoutubeHome() {
        super();

        try {
            mainContent = findOnPage(MAIN_CONTENT_FINDER);
        } catch (TimeoutException e) {
            fail("Main content panel not found");
        }
    }

    public void performSearch(String query) {
        DomElement searchBox = mainContent.findChildBy(By.cssSelector("input#search"));
        searchBox.clear();
        searchBox.enterText(query);

        DomElement searchButton = mainContent.findChildBy(By.cssSelector("button#search-icon-legacy"));
        searchButton.click();

        SeleniumUtils.getShortWait().until(
                driver -> driver.findElement(By.cssSelector("ytd-search")));
    }

    public List<YoutubeSearchResults> getSearchResults() {
        DomElement searchResults = mainContent.findChildBy(By.cssSelector("ytd-search"));
        return searchResults.findChildrenBy(By.cssSelector("ytd-video-renderer"))
                .stream()
                .map(YoutubeSearchResults::new)
                .toList();
    }
}
