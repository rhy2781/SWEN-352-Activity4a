package edu.rit.swen253.page.google;

import static org.junit.jupiter.api.Assertions.fail;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

/**
 * Object pertaining to the structure of the google search page "https://google.com"
 * @author <a href='mailto:rhy2781@rit.edu'>Robert Yamasaki</a>
 */
public class GoogleSearchPage extends AbstractPage{
    private static final By MAIN_CONTENT_FINDER = By.className("L3eUgb");

    private DomElement mainContentPanel;

    public GoogleSearchPage(){
        super();
        try{
            mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
        }catch(TimeoutException e){
            fail("Main content panel is not found");
        }
    }

    /**
     * Insert a string query into the search box and return the current value stored within the search box 
     * 
     * @param query the string that will be inserted into the search box
     * @return the input value in the search box
     */
    public String insertTextIntoSearchComponent(String query){
        DomElement search = mainContentPanel.findChildBy(By.name("q"));
        search.enterText(query);    
        return search.getInputValue();
    }

    /**
     * Execute a search by hitting the enter button within the text area
     */
    public void executeSearch(){
        DomElement search = mainContentPanel.findChildBy(By.name("q"));  
        search.enterText(Keys.ENTER);
    }

}
