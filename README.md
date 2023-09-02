# My Personal Project

## Book Log

The application allows users to record and manage the books they finished reading to maintain a personal myBook log. 
It offers customizable tags, searching, sorting and filtering functions, so users can curate their virtual library. 

### Feature Details

**Book Logging:**
- Users can log books they have finished reading to their library, including details such as myBook title, author, 
  and published date.
- Each entry has optional fields that allow users to record additional information, such as genre, the date 
  finished reading, and personal rating.
- Users can record memorable quotes along with their page number and any comments to the excerpt section of a myBook
  entry.

**Library Management:**
- Users can view and manage their myBook library by adding new myBook entries, editing existing entries, and deleting
  entries as needed.
- The app supports organizing books by different sorting criteria, such as title, author, rating, or other 
  customized tags.
- Users can search for myBook titles or authors' names.

**Tags and Filtering:**
- Users can apply filters to narrow down library display, enabling them to view specific genres, books by a 
  particular author, or books within a certain time frame.
- The app supports customizable tags. Users can create and manage their own tags, allowing users to categorize 
  books based on genres or any other relevant criteria.

I enjoy reading and keep a reading log on pen and paper. It would be more efficient to digitally manage my 
reading log so that I can perform sorting/filtering and access my favorite quotes easily. This app will be useful 
for readers who wish to maintain a record of their reading history and curate their tailored myBook archive.

### User Stories
- As a user, I want to be able to log a finished myBook with its information into my library.
- As a user, I want to be able to edit or delete any existing entries.
- As a user, I want to be able to filter and view all the books of a specific genre in my library.
- As a user, I want to be able to add my favorite quotes to the excerpt section of a myBook entry.
- As a user, I want to be able to choose to save my library to file when quitting the app.
- As a user, I want to be able to load a library from existing files when starting the app.
- As a user, I want to be able to delete an existing library.

## Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by clicking on "LOG A NEW BOOK"
  - The title field cannot empty, but you may skip the other fields by leaving them empty.
  - Click on "SAVE" to add the book or "CANCEL" to go back.
- You can generate the second required action related to adding Xs to a Y by clicking on "SEARCH BY AUTHOR" or 
  "SEARCH BY GENRE".
- You can locate my visual component by clicking "VIEW ALL BOOKS".
- You can save the state of my application by clicking "QUIT".
- You can reload the state of my application by typing in the name of an existing library when starting the app.
  (e.g. "myLibrary")

## Phase 4: Task 2 (Sample EventLog)
- Tue Aug 08 20:56:30 PDT 2023: Added book "A Luminous Republic" to library.
- Tue Aug 08 20:56:48 PDT 2023: Searched for book "The Silmarillion".
- Tue Aug 08 20:57:08 PDT 2023: Searched for books in the tag "J.R.R. Tolkien".
- Tue Aug 08 20:57:18 PDT 2023: Searched for books in the tag "high fantasy".

## Phase 4: Task 3
If I had more time for the project, I would refactor my MyBook and Quote classes with a composite pattern. I realized 
that the two share a few common operations such as editing/deleting/adding. It would make the project easier to maintain 
if an interface/abstract class was extracted that could manage individual object (MyBook) and their compositions 
(List< Quote >) uniformly.

Another improvement I wanted to do is refactoring my Managers classes. I could extract a superclass that stores some of 
the common behaviours such as handling click events by resetting the main panel. In addition, it would also make the 
project structure clearer if the private classes for click handlers were moved to the managers or their super class.
That will improve the cohesion of the display classes by transferring the responsibilities of responding to events to 
their managers.
