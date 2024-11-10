package edu.rit.swen253.test.google;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.google.GoogleSearchPage;
import edu.rit.swen253.page.google.GoogleSearchResultsPage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

public class GoogleTest extends AbstractWebTest {

    private GoogleSearchPage searchPage;
    private GoogleSearchResultsPage resultsPage;
    private static final String query = "openai";
    private static final String expectedURL = "https://openai.com/";
    private static final Logger LOGGER = Logger.getLogger(GoogleSearchResultsPage.class.getName());


    /**
     * Navigate to the Google Home page
     */
    @Test
    @Order(1)
    public void navigateToGooglePage(){
        searchPage = navigateToPage("https://google.com", GoogleSearchPage::new);
    }

    /**
     * Test that we insert the text and submit the search
     */
    @Test
    @Order(2)
    public void searchFromGoogle(){
        String res = searchPage.insertTextIntoSearchComponent(query);
        assertEquals(query, res);
        searchPage.executeSearch();
        searchPage.waitUntilGone();
        resultsPage = assertNewPage(GoogleSearchResultsPage::new);
    }


    /**  
     * Validate that the correct search was executed, verify the url and 
     * title of the first element, and navigate to that page
     */
    @Test
    @Order(3)
    public void searchResultsPage(){
        // ensure that our search component on the results page shows the same phrase that we queried for
        assertEquals(query, resultsPage.getQueryInSearchComponent());

        LOGGER.info("-----------------------------");
        LOGGER.info("First result Title: " +  resultsPage.getFirstResultTitle());
        LOGGER.info("First result URL: " + resultsPage.getFirstResultURL());
        LOGGER.info("-----------------------------");

        resultsPage.clickFirstResult();
        resultsPage.waitUntilGone();

        // The page URL for pokemon blocks interactions with selenium automated test
        SimplePage page = assertNewPage(SimplePage::new);
        assertEquals(expectedURL, page.getURL());
        System.out.println(page.getURL());
    }
}
