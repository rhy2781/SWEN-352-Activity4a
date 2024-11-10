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
    public void insertTextInSearchField(){
        DomElement search = searchPage.getSearchComponent();
        search.enterText("pokemon");    
        assertEquals("pokemon", search.getInputValue());
        search.enterText(Keys.ENTER);
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
        // ensure that our search bar contains the same text that we searched with
        DomElement search = resultsPage.getSearchComponent();
        assertEquals("pokemon", search.getInputValue());

        // get the url and link for the first result
        DomElement result = resultsPage.getFirstSearchResult();
        String url = result.findChildBy(By.xpath(".//a")).getAttribute("href");
        String title = result.findChildBy(By.xpath(".//h3")).getText();
        
        LOGGER.info("-----------------------------");
        LOGGER.info("First result Title: " +  title);
        LOGGER.info("First result URL: " + url);
        LOGGER.info("-----------------------------");


        // click the first result
        result.findChildBy(By.xpath(".//a")).click();
        resultsPage.waitUntilGone();

        // create new page and get url to compare
        SimplePage page = assertNewPage(SimplePage::new);
        assertEquals(url, page.getURL());
    }
}
