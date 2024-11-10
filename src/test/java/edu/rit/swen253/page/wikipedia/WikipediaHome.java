package edu.rit.swen253.page.wikipedia;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;
public class WikipediaHome extends AbstractPage {

    private static final Logger LOGG = Logger.getLogger(WikipediaHome.class.getName());
    private static final By MAIN_CONTENT_FINDER = By.tagName("main");
    private static final By MAIN_CONTENT_SEARCH = By.cssSelector("div#mw-mf-viewport");  
    private static final By SEARCH_RESULTS_CONTAINER = By.cssSelector("div.mw-search-results-container");

    private DomElement mainContent;

    public WikipediaHome() {
        super();

        try {
            mainContent = findOnPage(MAIN_CONTENT_FINDER);
        } catch (TimeoutException e) {
            fail("Main content panel not found");
        }
    }

    public void performSearch(String query) {
        // Locate the search input box and search button within the main content
        DomElement searchBox = mainContent.findChildBy(By.cssSelector("input#searchInput"));
        DomElement searchButton = mainContent.findChildBy(By.cssSelector("button.pure-button.pure-button-primary-progressive"));

        // Wait until the search box is clickable
        ((JavascriptExecutor) SeleniumUtils.getDriver()).executeScript("arguments[0].scrollIntoView(true);", searchButton.getWebElement());

        // Clear the search box and enter the query
        searchBox.clear();
        searchBox.enterText(query);
        LOGG.info("Searching for: " + query);
        searchButton.click();
        LOGG.info("Search button clicked");

        // Wait for the new content to load after clicking search
      
        SeleniumUtils.getLongWait().until(ExpectedConditions.stalenessOf(searchButton.getWebElement()));
        LOGG.info("Search results container found");

        // Reinitialize main content as the page has changed
        try {
            mainContent = findOnPage(MAIN_CONTENT_SEARCH);
            LOGG.warning("Main content overwritten");
        }  catch (TimeoutException e) {
            LOGG.warning("Main content after search not found");
        } catch (StaleElementReferenceException e) {
            // Re-find the main content and perform the search again if the element is stale
            LOGG.warning("Caught StaleElementReferenceException, retrying the search...");
            mainContent = findOnPage(MAIN_CONTENT_SEARCH);
            performSearch(query);  // Retry the search with fresh references
        }
    }

    public List<WikipediaSearchResults> getSearchResults() {
        // Wait for the search results container to be present (new page structure)
        SeleniumUtils.getLongWait().until(ExpectedConditions.presenceOfElementLocated(SEARCH_RESULTS_CONTAINER));
    
        // Re-initialize the main content and search results after the page reloads
        mainContent = findOnPage(MAIN_CONTENT_SEARCH);
    
        // Find the container of search results
        DomElement searchResultsContainer = mainContent.findChildBy(SEARCH_RESULTS_CONTAINER);
    
        // Make sure the list is not empty (valid results)
        List<DomElement> resultElements = searchResultsContainer.findChildBy(By.cssSelector("ul.mw-search-results"))
                .findChildrenBy(By.cssSelector("li.mw-search-result"));
    
        if (resultElements.isEmpty()) {
            LOGG.warning("No search results found.");
            return List.of();  // Return an empty list if no results are found
        }
    
        // Return the search results
        //return resultElements.stream()
        //        .map(WikipediaSearchResults::new)
          //      .toList();
    }
}
