package edu.rit.swen253.page.youtube;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class YoutubeHome extends AbstractPage {

    private static final Logger LOGG = Logger.getLogger(YoutubeHome.class.getName());

    public YoutubeHome() {
        super();

    }

    public void performSearch(String query) {
        DomElement searchBox = findOnPage(By.cssSelector("input#search"));
        searchBox.clear();
        searchBox.enterText(query);

        DomElement searchButton = findOnPage(By.cssSelector("button#search-icon-legacy"));
        searchButton.click();

        SeleniumUtils.getShortWait().until(
                driver -> driver.findElement(By.cssSelector("ytd-search")));
    }

    public List<YoutubeSearchResults> getSearchResults() {
        DomElement searchResults = findOnPage(By.cssSelector("ytd-search"));
        return searchResults.findChildrenBy(By.cssSelector("ytd-video-renderer"))
                .stream()
                .map(YoutubeSearchResults::new)
                .toList();
    }
}
