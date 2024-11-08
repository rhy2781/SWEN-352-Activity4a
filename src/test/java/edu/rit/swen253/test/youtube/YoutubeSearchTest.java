package edu.rit.swen253.test.youtube;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.youtube.YoutubeHome;
import edu.rit.swen253.page.youtube.YoutubeSearch;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YoutubeSearchTest extends AbstractWebTest {

    private YoutubeHome homePage;
    private YoutubeSearch searchPage;

    @Test
    @Order(1)
    public void navigateToHomePage() {
        homePage = navigateToPage("https://www.youtube.com/", YoutubeHome::new);
    }
}