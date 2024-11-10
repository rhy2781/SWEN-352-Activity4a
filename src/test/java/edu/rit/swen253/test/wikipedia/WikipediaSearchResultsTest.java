package edu.rit.swen253.test.wikipedia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.wikipedia.WikipediaHome;
import edu.rit.swen253.page.wikipedia.WikipediaSearchResults;
import edu.rit.swen253.page.youtube.YouTubeHome;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WikipediaSearchResultsTest extends AbstractWebTest {

    private WikipediaHome homePage;
    private WikipediaSearchResults firstSearchResults;
    private static final Logger LOGGER = Logger.getLogger(WikipediaHome.class.getName());


   
    @Test
    @Order(1)
    public void navigateToHomePage() {
        homePage = navigateToPage("https://www.wikipedia.org/", WikipediaHome::new);
    }


    
    @Test
    @Order(2)
    public void testPerformSearchResults() {
        homePage.performSearch("topic: realm of the mad god");
        List<WikipediaSearchResults> searchResults = homePage.getSearchResults();

        assertTrue(searchResults.size() > 0);
        firstSearchResults = searchResults.get(0);

        LOGGER.info("-----------------------------");
        LOGGER.info("First result Title: " +  firstSearchResults.getTitle());
        LOGGER.info("First result URL: " + firstSearchResults.getUrl());
        LOGGER.info("-----------------------------");
    }
    
   

    /* 
    @Test
    @Order(3)
    public void testFirstResult() {
        String title = firstSearchResults.getTitle();
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
    */
    
}
