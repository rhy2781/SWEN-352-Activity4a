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

    public DomElement getSearchComponent(){
        return mainContentPanel.findChildBy(By.name("q"));
    }

    public DomElement getFirstSearchResult(){
        DomElement rso = mainContentPanel.findChildBy(By.id("rso"));
        return rso.findChildBy(By.cssSelector("div.g"));
    }
    public DomElement getRHS(){
        return mainContentPanel.findChildBy(By.id("rhs"));
    }
}
