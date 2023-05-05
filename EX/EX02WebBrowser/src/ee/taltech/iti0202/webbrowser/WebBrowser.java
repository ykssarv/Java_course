package ee.taltech.iti0202.webbrowser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebBrowser {
    private String homePage;
    private String currentPage;
    private ArrayList<String> history;
    private ArrayList<String> backHistory;
    private ArrayList<String> bookMarks;
    private int backHistoryIndex;

    /**
     * Browse web.
     */
    public WebBrowser() {
        this.homePage = "google.com";
        this.currentPage = "google.com";
        this.history = new ArrayList<String>();
        this.backHistory = new ArrayList<String>();
        this.bookMarks = new ArrayList<String>();
        this.history.add("google.com");
        this.backHistory.add("google.com");
        this.backHistoryIndex = 0;
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        goTo(homePage);
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        if (backHistoryIndex == 0) {
            return;
        } else {
            --backHistoryIndex;
            currentPage = backHistory.get(backHistoryIndex);
            history.add(currentPage);
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        if (backHistoryIndex == backHistory.size() - 1) {
            return;
        } else {
            ++backHistoryIndex;
            currentPage = backHistory.get(backHistoryIndex);
            history.add(currentPage);
        }
    }

    /**
     * Go to a webpage.
     *
     * @param url to go to
     */
    public void goTo(String url) {
        if (currentPage.equals(url)) {
            return;
        }
        currentPage = url;
        history.add(url);

        if (backHistoryIndex < backHistory.size() - 1) {
            backHistory.subList(backHistoryIndex + 1, backHistory.size()).clear();
        }
        backHistory.add(url);
        backHistoryIndex++;
    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (!bookMarks.contains(currentPage)) {
            bookMarks.add(currentPage);
        }
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookMarks.remove(bookmark);
    }

    /**
     * Bookmark.
     * @return bookmarks in browser
     */
    public List<String> getBookmarks() {
        return bookMarks;
    }

    /**
     * Homepage.
     * @param homePage to set
     */
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }


    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        Map<String, Integer> wordCounter = new LinkedHashMap<>();
        for (String page: history) {
            int currentValue;
            if (wordCounter.containsKey(page)) {
                currentValue = (int) wordCounter.get(page);
            } else {
                currentValue = 0;
            }
            wordCounter.put(page, currentValue + 1);
        }
        List<Integer> maxValues = Arrays.asList(0, 0, 0);
        List<String> topPages = Arrays.asList("", "", "");
        for (Map.Entry<String, Integer> entry: wordCounter.entrySet()) {
            if (entry.getValue() > maxValues.get(2)) {
                maxValues.set(2, entry.getValue());
                topPages.set(2, entry.getKey());
                if (entry.getValue() > maxValues.get(1)) {
                    maxValues.set(2, maxValues.get(1));
                    maxValues.set(1, entry.getValue());
                    topPages.set(2, topPages.get(1));
                    topPages.set(1, entry.getKey());
                    if (entry.getValue() > maxValues.get(0)) {
                        maxValues.set(1, maxValues.get(0));
                        maxValues.set(0, entry.getValue());
                        topPages.set(1, topPages.get(0));
                        topPages.set(0, entry.getKey());
                    }
                }
            }
        }
        String topPagesAndVisits = " ";
        for (int i = 0; i < 3; i++) {
            if (maxValues.get(i) == 0) {
                break;
            } else if (maxValues.get(i) == 1) {
                topPagesAndVisits = topPagesAndVisits + topPages.get(i) + " " + "-" + " " + maxValues.get(i) + " "
                        + "visit" + "\n";
            } else {
                topPagesAndVisits = topPagesAndVisits + topPages.get(i) + " " + "-" + " " + maxValues.get(i) + " "
                        + "visits" + "\n";
            }
        } return topPagesAndVisits;

    }
    /**
     * Returns a list of all visited pages.
     *
     * Not to be confused with pages in your back-history.
     *
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        return history;
    }


    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return currentPage;
    }
}
