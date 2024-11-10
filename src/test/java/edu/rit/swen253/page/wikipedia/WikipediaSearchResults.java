package edu.rit.swen253.page.wikipedia;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class WikipediaSearchResults {

    private final DomElement wikiElement;

    public WikipediaSearchResults(final DomElement viewContainer) {
        this.wikiElement = viewContainer;
    }

    public String getTitle() {
        return wikiElement.findChildBy(By.cssSelector("a[title='Realm of the Mad God']")).getText();
    }

    public String getUrl() {
        return wikiElement.findChildBy(By.cssSelector("a[data-serp-pos='0']")).getAttribute("href");
    }

    public void click() {
        wikiElement.findChildBy(By.cssSelector("a[data-serp-pos='0']")).click();
    }
}
