package edu.rit.swen253.page.youtube;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

/**
 * Represents a single YouTube video from search result and provides methods to
 * interact with it.
 * 
 * @author Austyn Wright
 */
public class YouTubeSearchResult {

    private final DomElement videoElement;

    /**
     * Constructs a new YouTubeSearchResult object with the gvien video container.
     * 
     * @param videoElement The DomElement representing the video container element.
     */
    public YouTubeSearchResult(final DomElement videoElement) {
        this.videoElement = videoElement;
    }

    /**
     * Retrieves the title of the video.
     *
     * @return The title text of the video.
     */
    public String getTitle() {
        return videoElement.findChildBy(By.cssSelector("a#video-title")).getText();
    }

    /**
     * Retrieves the URL of the video.
     *
     * @return The href attribute value of the video link.
     */
    public String getUrl() {
        return videoElement.findChildBy(By.cssSelector("a#video-title")).getAttribute("href");
    }

    /**
     * Clicks on the video title to navigate to the video's page.
     */
    public void click() {
        videoElement.findChildBy(By.cssSelector("a#video-title")).click();
    }
}
