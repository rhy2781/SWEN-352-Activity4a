package edu.rit.swen253.test.google;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.google.GoogleSearchPage;
import edu.rit.swen253.page.google.GoogleSearchResultsPage;
import edu.rit.swen253.test.AbstractWebTest;

/**
 * Test class to search for a query and navigate to the first element
 * @author <a href='mailto:rhy2781@rit.edu'>Robert Yamasaki</a>
 */
public class GoogleTest extends AbstractWebTest {

    private GoogleSearchPage searchPage;
    private GoogleSearchResultsPage resultsPage;
    private static final String query = "pokemon";
    private static final String expectedURL = "https://www.pokemon.com/us";
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
     * Insert the query into the search component and verify that the query was inserted correctly before
     * executing the search
     */
    @Test
    @Order(2)
    public void searchFromGoogle(){
        assertEquals(query, searchPage.insertTextIntoSearchComponent(query));
        searchPage.executeSearch();
        searchPage.waitUntilGone();
        resultsPage = assertNewPage(GoogleSearchResultsPage::new);
    }


    /**  
     * Validate that the correct search was executed
     * 1. The search element should have the correct query
     * 2. Display the url and the title associated with the first element.
     * 3. Navigate to the corresponding page and verify the URL
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
        // The content of the page may not load, but the url will due to data scraping preventions
        SimplePage page = assertNewPage(SimplePage::new);
        assertEquals(expectedURL, page.getURL());
    }
}
