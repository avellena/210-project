package model;

import java.util.HashSet;

// Represents a tag with a tagName and a list of book ids that belong to this tag
// e.g. an author tag can have a tagName = "Garcia Marquez" and a list of books written by this author
//      a genre tag can have a tagName = "fiction" and a list of books tha belong to this genre
public class Tag {
    private String tagName;
    private HashSet<Integer> works;  // list of ids of all the books written by the tag

    // EFFECTS: constructs a tag with tagName. Tag initially has no works.
    public Tag(String tagName) {
        this.tagName = tagName;
        works = new HashSet<>();
    }

    // EFFECT: constructs a tag with tagName and list of works.
    public Tag(String tagName, HashSet<Integer> works) {
        this.tagName = tagName;
        this.works = works;
    }

    // REQUIRES: tag name is non-empty
    // MODIFIES: this
    // EFFECTS: set the tag name
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    // REQUIRES: bookId not already in works
    // MODIFIES: this
    // EFFECTS: add bookId to list of works.
    public void addWork(int bookId) {
        works.add(bookId);
    }

    // MODIFIES: this
    // EFFECTS: remove bookId from the list of works if it exists. Otherwise, do nothing.
    public void removeWork(int bookId) {
        works.remove(bookId);
    }

    // getters

    public HashSet<Integer> getWorks() {
        return works;
    }

    public String getTagName() {
        return tagName;
    }

}
