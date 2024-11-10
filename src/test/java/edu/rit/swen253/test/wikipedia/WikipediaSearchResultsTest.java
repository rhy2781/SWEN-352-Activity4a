package edu.rit.swen253.test.wikipedia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.Keys;

import edu.rit.swen253.page.google.GoogleSearchResultsPage;
import edu.rit.swen253.page.wikipedia.WikipediaHome;
import edu.rit.swen253.page.wikipedia.WikipediaSearchPage;
import edu.rit.swen253.page.wikipedia.WikipediaSearchResults;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WikipediaSearchResultsTest extends AbstractWebTest {

    private WikipediaSearchPage searchPage;
    private WikipediaSearchResults firstSearchResults;
    private static final Logger LOGGER = Logger.getLogger(WikipediaSearchResults.class.getName());

   
    @Test
    @Order(1)
    public void navigateToHomePage() {
        searchPage = navigateToPage("https://www.wikipedia.org/", WikipediaSearchPage::new);
        LOGGER.info("Home Page Navigated");
    }


    
    @Test
    @Order(2)
    public void testPerformSearchResults() {
        DomElement search = searchPage.getSearchComponent();
        search.enterText("topic: realm of the mad god");
        assertEquals("topic: realm of the mad god", search.getInputValue());
        search.enterText(Keys.ENTER);
        searchPage.waitUntilGone();
        firstSearchResults = assertNewPage(WikipediaSearchResults::new);
        
        LOGGER.info("-----------------------------");
        LOGGER.info("First result Title: " +  firstSearchResults.getSearchTitle());
        LOGGER.info("First result URL: " + firstSearchResults.getUrl());
        LOGGER.info("-----------------------------");
        
    }
    
   

    
    @Test
    @Order(3)
    public void testFirstResult() {
        String title = firstSearchResults.getSearchTitle();
        assertEquals("Realm of the Mad God", title);
    }

    
    @Test
    @Order(4)
    public void testClickFirstResult() {
        firstSearchResults.click();

        String expectedTitle = "Realm of the Mad God - Wikipedia";
        SeleniumUtils.getShortWait().until(
                driver -> driver.getTitle().contains(expectedTitle));

        String actualTitle = SeleniumUtils.getDriver().getTitle();
        assertTrue(expectedTitle.equals(actualTitle), "Page title does not match expected title");
    }
    
    
}
