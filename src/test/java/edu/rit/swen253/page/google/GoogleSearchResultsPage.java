package edu.rit.swen253.page.google;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

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

    public String getQueryInSearchComponent(){
        DomElement search = mainContentPanel.findChildBy(By.name("q"));
        return search.getInputValue();
    }

    public DomElement getFirstResult(){
        DomElement results = mainContentPanel.findChildBy(By.id("rso"));
        DomElement first = results.findChildBy(By.cssSelector("div.g"));
        return first;
    }

    public String getFirstResultURL(){
        DomElement first = getFirstResult();
        return first.findChildBy(By.xpath(".//a")).getAttribute("href");
    }

    public String getFirstResultTitle(){
        DomElement first = getFirstResult();
        return first.findChildBy(By.xpath(".//h3")).getText();
    }

    public void clickFirstResult(){
        DomElement first = getFirstResult();
        first.findChildBy(By.xpath(".//a")).click();
    }
}
