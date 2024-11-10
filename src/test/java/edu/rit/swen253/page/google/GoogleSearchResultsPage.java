package edu.rit.swen253.page.google;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

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

    public List<DomElement> getSearchResults(){
        DomElement searchResults = mainContentPanel.findChildBy(By.id("kp-wp-tab-overview"));
        List<DomElement> results = searchResults.findChildrenBy(By.xpath(".//cite"));
        results.removeIf(n -> n.getText().equals(""));
        return results;
    }

    public DomElement getSupplementalEquation(){
        // TODO
        DomElement image = mainContentPanel.findChildBy(By.xpath("//*[data-attrid='formula-image']"));
        System.out.println();
        System.out.println(image.findChildBy(By.xpath(".//img")).getAttribute("alt"));


        // DomElement searchResults = mainContentPanel.findChildBy(By.id("kp-wp-tab-overview"));
        // List<DomElement> results = searchResults.findChildrenBy(By.xpath(".//img"));
        // results.removeIf(n -> n.getText().equals(""));
        return image;
    }
}
