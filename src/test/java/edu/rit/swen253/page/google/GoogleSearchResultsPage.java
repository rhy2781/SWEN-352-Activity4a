package edu.rit.swen253.page.google;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

/**
 * Object pertaining to the page that results from executing a search at "https://google.com"
 * @author <a href='mailto:rhy2781@rit.edu'>Robert Yamasaki</a>
 */
public class GoogleSearchResultsPage extends AbstractPage{
    private static final By MAIN_CONTENT_FINDER = By.className("main");

    private DomElement mainContentPanel;

    public GoogleSearchResultsPage(){
        super();
        try{
            mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
        }catch(TimeoutException e){
            fail("Main content panel is not found");
        }
    }

    /**
     * @return the current string in the search component
     */
    public String getQueryInSearchComponent(){
        DomElement search = mainContentPanel.findChildBy(By.name("q"));
        return search.getInputValue();
    }

    /**
     * Get the first result element within the div that stores the results(id="rso")
     * The individual result elements are marked by the className "g"
     * 
     * @return the domElement corresponding to the first result
     */
    public DomElement getFirstResult(){
        DomElement results = mainContentPanel.findChildBy(By.id("rso"));
        DomElement first = results.findChildBy(By.className("g"));
        return first;
    }

    /**
     * get the <a> element nested under the "g" class domElement in order to find the corresponding url
     * @return the url corresponding to the first element
     */
    public String getFirstResultURL(){
        DomElement first = getFirstResult();
        return first.findChildBy(By.xpath(".//a")).getAttribute("href");
    }

    /**
     * get the <h3> element nested under the "g" class domElement in order to get the corresponding title
     * @return the title corresponding to the first element
     */
    public String getFirstResultTitle(){
        DomElement first = getFirstResult();
        return first.findChildBy(By.xpath(".//h3")).getText();
    }

    /**
     * Navigate to the url corresponding to the first result domElement by clicking on the element with the
     * className "q0vns", 
     */
    public void clickFirstResult(){
        DomElement first = getFirstResult();
        first.findChildBy(By.className("q0vns")).click();
    }
}
