package edu.rit.swen253.page.wikipedia;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
/**
 * Object pertaining to the structure of the wikipedia gome page
 * @author <a href='mailto:tsf2802@rit.edu'>Takumi Fukuzawa</a>
 */
public class WikipediaSearchPage extends AbstractPage {
     private static final By MAIN_CONTENT_FINDER = By.tagName("main");

    private DomElement mainContentPanel;

    public WikipediaSearchPage(){
        super();
        try{
            mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
        }catch(TimeoutException e){
            fail("Main content panel is not found");
        }
    }
    /**
     * A method of getting the searchinput content. 
     * @return the search component for the search
     */
    public DomElement getSearchComponent(){
        DomElement res = null;
        res = mainContentPanel.findChildBy(By.cssSelector("input#searchInput"));
        return res;
    }
}
