package ee.taltech.iti0202.webbrowser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

class WebBrowserTest {
    WebBrowser testBrowser;

    /**
     * Test. Setup.
     */
    @BeforeEach
    void setUp() {
        testBrowser = new WebBrowser();
    }

    /**
     * Test. Homepage.
     */
    @Test
    void homePage() {
        testBrowser.goTo("twitter.com");
        testBrowser.homePage();
        assertEquals("google.com", testBrowser.getCurrentUrl());
    }

    /**
     * Test. Back.
     */
    @Test
    void back() {
        testBrowser.goTo("twitter.com");
        testBrowser.back();
        assertEquals("google.com", testBrowser.getCurrentUrl());
    }

    /**
     * Test. Forward.
     */
    @Test
    void forward() {
        testBrowser.goTo("twitter.com");
        testBrowser.back();
        testBrowser.forward();
        assertEquals("twitter.com", testBrowser.getCurrentUrl());
    }

    /**
     * Test. Go to.
     */
    @Test
    void goTo() {
        testBrowser.goTo("twitter.com");
        assertEquals("twitter.com", testBrowser.getCurrentUrl());
    }

    /**
     * Test. Set homepage.
     */
    @Test
    void setHomePage() {
        testBrowser.setHomePage("twitter.com");
        testBrowser.homePage();
        assertEquals("twitter.com", testBrowser.getCurrentUrl());
    }

    /**
     * Test. Get current URL.
     */
    @Test
    void getCurrentUrl() {
        assertEquals("google.com", testBrowser.getCurrentUrl());
    }

    /**
     * Test. Go to page and back twice gives original URL.
     */
    @Test
    void goToPageAndBackTwiceGivesOriginalUrl() {
        testBrowser.goTo("test.com");
        testBrowser.back();
        testBrowser.goTo("test2.com");
        testBrowser.back();
        assertEquals("google.com", testBrowser.getCurrentUrl());
    }

    /**
     * Test. Going to current page does not change history.
     */
    @Test
    void goingToCurrentPageDoesNotChangeHistory() {
        testBrowser.goTo("google.com");
        assertEquals(Collections.singletonList("google.com"), testBrowser.getHistory());
    }
}
