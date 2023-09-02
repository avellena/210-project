package model;

import exception.InvalidInputException;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static java.lang.Character.isDigit;

// Represents a book with title, author name, date finished reading, genre, rating, comment, and quotes
public class MyBook {
    private static int nextBookId = 1;
    private int id;
    private String title;
    private String author;
    private String dateFinishedReading;
    private String genre;
    private int rating;
    private String comment;
    private ArrayList<Quote> quotes;



    // REQUIRES: title is non-empty string.
    // EFFECTS: Constructs a book with the given title. Book is assigned a unique id and an empty quotes list.
    //          - default dateFinishedReading is 0000-00-00
    //          - default rating is 0
    //          - default comment is "You didn't write a comment."
    public MyBook(String title) {
        comment = "";
        dateFinishedReading = "0000-00-00";
        rating = 0;
        quotes = new ArrayList<>();
        this.title = title;
        id = nextBookId;
        nextBookId++;
    }

    // REQUIRES:- dateFinishedReading format is "yyyy-MM-dd"
    //          - 1 <= rating <= 10
    // EFFECTS: Constructs a book with id, title, author, dateFinishedReading, genre, rating, comment, and quotes
    public MyBook(int id, String title, String author, String dateFinishedReading, String genre, int rating,
                  String comment, ArrayList<Quote> quotes) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.dateFinishedReading = dateFinishedReading;
        this.genre = genre;
        this.rating = rating;
        this.comment = comment;
        this.quotes = quotes;
        this.title = title;
        //this.id = nextBookId;
        //nextBookId++;
    }

    // REQUIRES: title is non-empty String.
    // MODIFIES: this
    // EFFECTS: Sets the title of the book.
    public void setTitle(String title) {
        this.title = title;
    }

    // MODIFIES: this
    // EFFECTS: Sets the corresponding author name of the book;
    public void setAuthorName(String author) {
        this.author = author;
    }

    // REQUIRES: date format is "yyyy-MM-dd"
    // MODIFIES: this
    // EFFECTS: Sets the date finished reading
    public void setDateFinishedReading(String date) {
        dateFinishedReading = date;
    }

    // MODIFIES: this
    // EFFECTS: Sets the corresponding genre id of the book.
    public void setGenreName(String genre) {
        this.genre = genre;
    }

    // REQUIRES: 1 <= rating <= 10
    // MODIFIES: this
    // EFFECTS: sets the rating of the book.
    public void setRating(int rating) {
        this.rating = rating;
    }

    // MODIFIES: this
    // EFFECTS: sets the comment for the book.
    public void setComment(String comment) {
        this.comment = comment;
    }

    // MODIFIES: this
    // EFFECTS: adds a quote to the quotes list
    public void addQuote(Quote quote) {
        quotes.add(quote);
    }

    // MODIFIES: this
    // EFFECTS: adds a quote to the quotes list
    public void removeQuote(Quote quote) {
        quotes.remove(quote);
    }

    // MODIFIES: this
    // EFFECTS: sets class field nextBookId
    public static void setNextBookId(int nextBookId) {
        MyBook.nextBookId = nextBookId;
    }

    // EFFECTS: validates given title.
    //        - throws exception if given title is empty
    public void validateTitle(String title) throws InvalidInputException {
        if (title.isBlank()) {
            throw new InvalidInputException("Title cannot be empty!");
        }
    }

    // EFFECTS: validates given date string.
    //        - throws exception if not in format yyyy-MM-dd
    public void validateDate(String date) throws InvalidInputException {
        Pattern dateFormat = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        if (!dateFormat.matcher(date).matches() && !date.isBlank()) {
            throw new InvalidInputException("Please follow the format yyyy-MM-dd!");
        }
    }

    // EFFECTS: validates given rating and return integer rating
    //        - IF rating string is empty, return 0
    //        - IF rating string is not an integer between 1 and 10, throws exception
    public int validateRating(String rating) throws InvalidInputException {
        if (rating.isBlank()) {
            return 0;
        }
        if (isInteger(rating)) {
            int ratingInt = Integer.parseInt(rating);
            if (ratingInt >= 1 && ratingInt <= 10) {
                return ratingInt;
            }
        }
        throw new InvalidInputException("Please input a rating between 1 and 10!");
    }

    // EFFECTS: returns true if str is an integer
    private boolean isInteger(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isDigit(c)) {
                return false;
            }
        }
        return true;
    }



    // getters:

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateFinishedReading() {
        return dateFinishedReading;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }

}
