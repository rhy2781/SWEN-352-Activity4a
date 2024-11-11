package edu.rit.swen253.page.wikipedia;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
/**
 * Test class to search for a query and navigate to the first element
 * @author <a href='mailto:tsf2802@rit.edu'>Takumi Fukuzawa/a>
 */
public class WikipediaSearchResults extends AbstractPage{
    private static final By MAIN_CONTENT_FINDER = By.className("mw-body");

    private DomElement mainContentPanel;

    
    public WikipediaSearchResults(){
        super();
        try{
            mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
        }catch(TimeoutException e){
            fail("Main content panel is not found 2");
        }
    }
    /**
     * Get the title of the first search item
     * @return the text corresponding to the first result
     */
    public String getSearchTitle(){
        DomElement searchResults = mainContentPanel.findChildBy(By.cssSelector("a[title='Realm of the Mad God']"));
        return searchResults.getText();
    }
    /**
     * Get the url of the first search item
     * @return the href of the link related to the first item
     */
    public String getUrl() {
        return mainContentPanel.findChildBy(By.cssSelector("a[data-serp-pos='0']")).getAttribute("href");
    }
    /**
     * Clicks the first item
     * @return None
     */
    public void click() {
        mainContentPanel.findChildBy(By.cssSelector("a[data-serp-pos='0']")).click();
    }

}
