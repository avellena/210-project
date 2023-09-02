package model;

// Represents a quote from the book with page number.
public class Quote {
    private String content;
    private int pageNum;

    // EFFECTS: constructs a quote with its content.
    public Quote(String content) {
        this.content = content;
    }

    // EFFECTS: constructs a quote with content and pageNum.
    public Quote(String content, int pageNum) {
        this.content = content;
        this.pageNum = pageNum;
    }

    // REQUIRES: pageNum > 0
    // MODIFIES: this
    // EFFECTS: sets the page number of the quote.
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    // MODIFIES: this;
    // EFFECTS: sets the content of the quote.
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getPageNum() {
        return pageNum;
    }
}
