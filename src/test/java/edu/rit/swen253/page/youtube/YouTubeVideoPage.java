package edu.rit.swen253.page.youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.SeleniumUtils;

/**
 * Represents a YouTube Video Page and provides methods to interact with it.
 * 
 * @author Austyn Wright
 */
public class YouTubeVideoPage extends AbstractPage {

    /**
     * Constructs a YouTubeVideoPage object and ensures the video title is loaded.
     */
    public YouTubeVideoPage() {
        super();

        SeleniumUtils.getShortWait().until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#title yt-formatted-string")));
    }

    /**
     * Retrieves the title of the video page.
     *
     * @return The title of the video page.
     */
    public String getTitle() {
        return SeleniumUtils.getDriver().getTitle();
    }
}
