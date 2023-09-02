package model;

import java.util.ArrayList;


// Represents a library with all book entries, list of authors and list of genres.
public class Library {
    private ArrayList<MyBook> myBooks;
    private ArrayList<Tag> authors;
    private ArrayList<Tag> genres;

    // EFFECTS: constructs a library with no collections in all fields
    public Library() {
        myBooks = new ArrayList<>();
        authors = new ArrayList<>();
        genres = new ArrayList<>();
    }

    // EFFECTS: constructs a library with given books, authors, and genres
    public Library(ArrayList<MyBook> books, ArrayList<Tag> authors, ArrayList<Tag> genres) {
        this.myBooks = books;
        this.authors = authors;
        this.genres = genres;
    }

    // MODIFIES: this
    // EFFECTS: adds a book entry to books and add this book to its genre/author tags;
    //        - logs event to EventLog
    public void addBook(MyBook myBook) {
        myBooks.add(myBook);
        String authorName = myBook.getAuthor();
        String genreName = myBook.getGenre();

        addBookToTag(myBook, authors, authorName);
        addBookToTag(myBook, genres, genreName);

        Event logBookEvent = new Event("Added book \"" + myBook.getTitle() + "\" to library.");
        EventLog.getInstance().logEvent(logBookEvent);
    }

    // MODIFIES: this
    // EFFECTS: - If tagOfThisBook is already in tags, add the book id to that tag
    //          - If tagOfThisBook is not in the tags, create a new tag with this book in its list of works
    //          - do nothing if tagOfThisBook is blank
    public void addBookToTag(MyBook myBook, ArrayList<Tag> tags, String tagOfThisBook) {
        if (tagOfThisBook.isBlank()) {
            return;
        }
        for (Tag tag: tags) {
            if (tag.getTagName().equals(tagOfThisBook)) {
                tag.addWork(myBook.getId());
                return;
            }
        }
        Tag newTag = new Tag(tagOfThisBook);
        newTag.addWork(myBook.getId());
        tags.add(newTag);
    }

    // MODIFIES: this
    // EFFECTS: thoroughly removes book from the library (removes the book from authors and genres as well)
    public void removeBook(MyBook myBook) {
        myBooks.remove(myBook);
        String authorName = myBook.getAuthor();
        String genreName = myBook.getGenre();

        removeBookFromTag(myBook, authors, authorName);
        removeBookFromTag(myBook, genres, genreName);
    }

    // REQUIRES: tags contains tagOfThisBook
    // MODIFIES: tags
    // EFFECTS: removes book from its tag's list of works.
    //          - If after removal, tag's list of works is empty, remove the tag.
    //          - do nothing if tagOfThisBook is blank
    public void removeBookFromTag(MyBook myBook, ArrayList<Tag> tags, String tagOfThisBook) {
        for (Tag tag: tags) {
            if (tag.getTagName().equals(tagOfThisBook)) {
                tag.removeWork(myBook.getId());
                if (tag.getWorks().isEmpty()) {
                    tags.remove(tag);
                }
                return;
            }
        }
    }

    // EFFECTS: returns the list of books in a tag; if tag not in tags, return null;
    public ArrayList<MyBook> findBooksInTag(String tagName, ArrayList<Tag> tags) {
        Event logBookEvent = new Event("Searched for books in the tag \"" + tagName + "\".");
        EventLog.getInstance().logEvent(logBookEvent);
        ArrayList<MyBook> booksInTag = new ArrayList<>();
        for (Tag tag : tags) {
            String thisTagName = tag.getTagName().toLowerCase();
            if (thisTagName.equals(tagName.toLowerCase())) {
                for (int id : tag.getWorks()) {
                    booksInTag.add(findBookWithId(id));
                }
                return booksInTag;
            }
        }
        return null;
    }

    // EFFECTS: returns the book object with given id. Returns null if not found.
    public MyBook findBookWithId(int id) {
        for (MyBook myBook : myBooks) {
            if (myBook.getId() == id) {
                return myBook;
            }
        }
        return null;
    }

    // EFFECTS: returns the book object with given title. Returns null if not found.
    public MyBook findBookWithTitle(String title) {
        Event logBookEvent = new Event("Searched for book \"" + title + "\".");
        EventLog.getInstance().logEvent(logBookEvent);
        for (MyBook myBook : myBooks) {
            String thisTitle = myBook.getTitle().toLowerCase();
            if (thisTitle.equals(title.toLowerCase())) {
                return myBook;
            }
        }
        return null;
    }




    // getters
    public ArrayList<MyBook> getBooks() {
        return myBooks;
    }

    public ArrayList<Tag> getAuthors() {
        return authors;
    }

    public ArrayList<Tag> getGenres() {
        return genres;
    }


}
