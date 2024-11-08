package edu.rit.swen253.test.google;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import edu.rit.swen253.page.google.GoogleSearchPage;
import edu.rit.swen253.test.AbstractWebTest;

public class GoogleTest extends AbstractWebTest {

    private GoogleSearchPage searchPage;

    @Test
    @Order(1)
    public void navigateToGooglePage(){
        searchPage = navigateToPage("https://google.com", GoogleSearchPage::new);
    }
}
