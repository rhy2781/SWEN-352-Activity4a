package edu.rit.swen253.page.youtube;

import org.openqa.selenium.By;

import edu.rit.swen253.utils.DomElement;

public class YoutubeSearchResults {

    private final DomElement videoElement;

    public YoutubeSearchResults(final DomElement viewContainer) {
        this.videoElement = viewContainer;
    }

    public String getTitle() {
        return videoElement.findChildBy(By.cssSelector("a#video-title")).getText();
    }

    public String getUrl() {
        return videoElement.findChildBy(By.cssSelector("a#video-title")).getAttribute("href");
    }

    public void click() {
        videoElement.findChildBy(By.cssSelector("a#video-title")).click();
    }
}
