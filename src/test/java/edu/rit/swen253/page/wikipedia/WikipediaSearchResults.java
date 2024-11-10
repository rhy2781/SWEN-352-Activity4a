package edu.rit.swen253.page.wikipedia;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

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

    public String getSearchTitle(){
        DomElement searchResults = mainContentPanel.findChildBy(By.cssSelector("a[title='Realm of the Mad God']"));
        //List<DomElement> results = searchResults.findChildrenBy(By.xpath(".//cite"));
        //results.removeIf(n -> n.getText().equals(""));
        return searchResults.getText();
    }

   
   // public WikipediaSearchResults(final DomElement viewContainer) {
     //   this.wikiElement = viewContainer;
   // }

    public String getUrl() {
        return mainContentPanel.findChildBy(By.cssSelector("a[data-serp-pos='0']")).getAttribute("href");
    }

    public void click() {
        mainContentPanel.findChildBy(By.cssSelector("a[data-serp-pos='0']")).click();
    }

}
