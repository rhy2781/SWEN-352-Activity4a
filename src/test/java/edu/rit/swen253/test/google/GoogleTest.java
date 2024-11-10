package edu.rit.swen253.test.google;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import edu.rit.swen253.page.google.GoogleSearchPage;
import edu.rit.swen253.page.google.GoogleSearchResultsPage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.DomElement;

public class GoogleTest extends AbstractWebTest {

    private GoogleSearchPage searchPage;
    private GoogleSearchResultsPage resultsPage;

    /**
     * Navigate to the Google Home page
     */
    @Test
    @Order(1)
    public void navigateToGooglePage(){
        searchPage = navigateToPage("https://google.com", GoogleSearchPage::new);
    }

    /**
     * Test that we insert the text, and that the text inserted is the correct value
     */
    @Test
    @Order(2)
    public void insertTextInSearchField(){
        DomElement search = searchPage.getSearchComponent();
        search.enterText("quadratic formula");    
        assertEquals("quadratic formula", search.getInputValue());
        search.enterText(Keys.ENTER);
        searchPage.waitUntilGone();

        resultsPage = assertNewPage(GoogleSearchResultsPage::new);
    }


    /**
     * Assert that appropriate search is displayed
     * 1. Search bar should contain the text "quadratic formula"
     * 2. First search result should be from Calculator Soup
     * 3. Appropriate image of the quadratic formula should be present
     */
    @Test
    @Order(3)
    public void searchResultsPage(){
        // ensure that our search bar contains the same text that we searched with
        DomElement search = resultsPage.getSearchComponent();
        assertEquals("quadratic formula", search.getInputValue());


        // get the citations for the list of websites that appear in the results
        List<DomElement> results = resultsPage.getSearchResults();
        for(DomElement e: results){
            System.out.println(e.getText());
        }

        DomElement first = results.get(0);
        DomElement second = results.get(1);
        assertEquals("https://www.calculatorsoup.com › Algebra", first.getText());
        assertEquals("https://www.khanacademy.org › math › algebra › using...", second.getText());


        // resultsPage.getSupplementalEquation();
    }





}
