package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

// reads local JSON files or writes working library to JSON files
public class LibraryFile implements ILibraryFile {

    private static String LIBRARY_PATH;
    private static String LIBRARY_FILE;

    // EFFECTS: initializes the reading/writing tool
    public LibraryFile() {

    }

    // MODIFIES: this
    // EFFECTS: sets the path of the local file to write to / read from
    public void setFile(String pathToDir, String libraryFile) {
        this.LIBRARY_FILE = libraryFile;
        this.LIBRARY_PATH = pathToDir;
    }

    // EFFECTS: - if LIBRARY_PATH / LIBRARY_FILE DNE, creates new file and writes entire library to file.
    //          - otherwise, writes entire library to existing file.
    public void writeLibrary(Library library) {
        JSONObject jsonLibrary = this.parseLibrary(library);

        File file = new File(LIBRARY_PATH, LIBRARY_FILE);

        try {
            // Check if the file exists, create if DNE
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.write(jsonLibrary.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // REQUIRES: no fields of type HashSet or ArrayList should be NULL
    // EFFECTS: returns a JSON object of Library
    private JSONObject parseLibrary(Library library) {
        JSONObject libraryJson = new JSONObject();

        JSONArray jsonBooks = this.parseMyBooks(library.getBooks(), libraryJson);
        JSONArray jsonAuthors = this.parseTags(library.getAuthors());
        JSONArray jsonGenres = this.parseTags(library.getGenres());

        libraryJson.put("books", jsonBooks);
        libraryJson.put("authors", jsonAuthors);
        libraryJson.put("genres", jsonGenres);
        return libraryJson;
    }

    // REQUIRE: quotes fields cannot be NULL
    // EFFECTS: returned a JSON array of MyBook
    private JSONArray parseMyBooks(ArrayList<MyBook> books, JSONObject libraryJson) {
        JSONArray jsonBooks = new JSONArray();
        int nextBookId = -1;
        for (MyBook book : books) {
            JSONObject jsonBook = new JSONObject();
            // sets the nextBookId field to the largest id in the library to avoid repeating ids
            jsonBook.put("id", book.getId());
            if (book.getId() > nextBookId) {
                nextBookId = book.getId();
            }
            jsonBook.put("title", book.getTitle());
            jsonBook.put("author", book.getAuthor());
            jsonBook.put("dateFinishedReading", book.getDateFinishedReading());
            jsonBook.put("genreName", book.getGenre());
            jsonBook.put("rating", book.getRating());
            jsonBook.put("comment", book.getComment());
            jsonBook.put("quotes", this.parseQuotes(book.getQuotes()));
            // add this book to books
            jsonBooks.put(jsonBook);
        }
        libraryJson.put("nextBookId", nextBookId + 1);
        return jsonBooks;
    }

    // EFFECTS: returned a JSON array of Quote
    private JSONArray parseQuotes(ArrayList<Quote> quotes) {
        JSONArray jsonQuotes = new JSONArray();
        for (Quote quote : quotes) {
            JSONObject jsonQuote = new JSONObject();
            jsonQuote.put("content", quote.getContent());
            jsonQuote.put("pageNum", quote.getPageNum());
            // add this quote to quotes
            jsonQuotes.put(jsonQuote);
        }
        return jsonQuotes;
    }

    // EFFECTS: returns a JSON array of Tag
    private JSONArray parseTags(ArrayList<Tag> tags) {
        JSONArray jsonTags = new JSONArray();
        for (Tag tag : tags) {
            JSONObject jsonTag = new JSONObject();
            jsonTag.put("works", this.parseWorks(tag.getWorks()));
            jsonTag.put("tagName", tag.getTagName());
            // add this tag to tags
            jsonTags.put(jsonTag);
        }
        return jsonTags;
    }

    // EFFECTS: returns a JSON array of works
    private JSONArray parseWorks(HashSet<Integer> works) {
        return new JSONArray(works);
    }

    // EFFECTS: returns a Library from JSON Data; returns null if Exception thrown by Files.readAllBytes()
    public Library readLibrary() {
        String file = LIBRARY_PATH + "/" + LIBRARY_FILE;
        try {
            String libraryString = new String(Files.readAllBytes(Paths.get(file)));
            JSONObject jsonLibrary = new JSONObject(libraryString);
            Library library = this.loadLibrary(jsonLibrary);
            return library;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // EFFECTS: reads all fields in the given library JSON object and returns such library
    private Library loadLibrary(JSONObject jsonLibrary) {
        ArrayList<MyBook> books = this.loadMyBooks(jsonLibrary.getJSONArray("books"));
        ArrayList<Tag> authors = this.loadTags(jsonLibrary.getJSONArray("authors"));
        ArrayList<Tag> genres = this.loadTags(jsonLibrary.getJSONArray("genres"));
        int nextBookId = jsonLibrary.getInt("nextBookId");
        MyBook.setNextBookId(nextBookId);

        Library library = new Library(books, authors, genres);
        return library;
    }

    // EFFECTS: returns an array of MyBook from JSON data
    private ArrayList<MyBook> loadMyBooks(JSONArray jsonBooks) {
        ArrayList<MyBook> books = new ArrayList<>();
        for (int i = 0; i < jsonBooks.length(); i++) {
            JSONObject jsonBook = jsonBooks.getJSONObject(i);
            int id = jsonBook.getInt("id");
            String title = jsonBook.getString("title");
            String author = jsonBook.getString("author");
            String dateFinishedReading = jsonBook.getString("dateFinishedReading");
            String genreName = jsonBook.getString("genreName");
            int rating = jsonBook.getInt("rating");
            String comment = jsonBook.getString("comment");
            ArrayList<Quote> quotes = this.loadQuotes(jsonBook.getJSONArray("quotes"));

            MyBook book = new MyBook(id, title, author, dateFinishedReading, genreName, rating, comment, quotes);
            books.add(book);
        }

        return books;
    }

    // EFFECTS: returns an array of Quote from JSON data
    private ArrayList<Quote> loadQuotes(JSONArray jsonQuotes) {
        ArrayList<Quote> quotes = new ArrayList<>();

        for (int i = 0; i < jsonQuotes.length(); i++) {
            JSONObject jsonBook = jsonQuotes.getJSONObject(i);

            String content = jsonBook.getString("content");
            int pageNum = jsonBook.getInt("pageNum");

            Quote quote = new Quote(content, pageNum);
            quotes.add(quote);
        }

        return quotes;
    }

    // EFFECTS: returns an array of Tag from JSON data
    private ArrayList<Tag> loadTags(JSONArray tagsJson) {
        ArrayList<Tag> tags = new ArrayList<>();

        for (int i = 0; i < tagsJson.length(); i++) {
            JSONObject jsonTag = tagsJson.getJSONObject(i);

            HashSet<Integer> works = this.loadWorks(jsonTag.getJSONArray("works"));
            String tagName = jsonTag.getString("tagName");

            Tag tag = new Tag(tagName, works);
            tags.add(tag);
        }

        return tags;
    }

    // EFFECTS: returns an array of bookId from JSON data
    private HashSet<Integer> loadWorks(JSONArray worksJson) {
        HashSet<Integer> works = new HashSet<>();

        for (int i = 0; i < worksJson.length(); i++) {
            Integer work = worksJson.getInt(i);
            works.add(work);
        }

        return works;
    }


    // EFFECTS: deletes LIBRARY_PATH / LIBRARY_FILE from /data directory
    public void clearDisk() {
        try {
            Path libraryFilePath = Paths.get(LIBRARY_PATH, LIBRARY_FILE);
            Files.delete(libraryFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


