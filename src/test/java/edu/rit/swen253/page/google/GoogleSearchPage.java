package edu.rit.swen253.page.google;

import static org.junit.jupiter.api.Assertions.fail;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

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

    public String insertTextIntoSearchComponent(String query){
        DomElement search = mainContentPanel.findChildBy(By.name("q"));
        search.enterText(query);    
        return search.getInputValue();
    }

    public void executeSearch(){
        DomElement search = mainContentPanel.findChildBy(By.name("q"));  
        search.enterText(Keys.ENTER);
    }

}
