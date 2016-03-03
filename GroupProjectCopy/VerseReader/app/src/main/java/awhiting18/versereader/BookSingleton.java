// Programmer Name: Andrew Whiting
// Date: 11/4/15
// Project Name: Verse Reader
// File Description: Defines a single static instance of a collection of Books

package awhiting18.versereader;

import java.io.Serializable;
import java.util.ArrayList;

public class BookSingleton{
    private static BookSingleton mLibrary; // A library is a static collection of books
    private ArrayList<Book>      mBooks;   // A collection of books

    // Private constructor
    private BookSingleton(){
        mBooks = new ArrayList<>();
    }

    // Gets the single book singleton
    public static BookSingleton get(){
        if(mLibrary == null)
            mLibrary = new BookSingleton();
        return mLibrary;
    }

    // Adds a book to the collection
    public void addBook(ArrayList<String []> book, String bookName){
        mBooks.add(new Book(book, bookName));
    }

    // Get a specific chapter object based off of its book name and chapter number
    public Chapter getChapter(String bookName, int chapterNumber){
        Chapter returnChapter = new Chapter();
        for(Book book : mBooks)
            if(book.getBookName().equals(bookName))
                for(Chapter chapter : book.getChapters())
                    if(chapter.getChapterNumber() == chapterNumber)
                        returnChapter = chapter;
        return returnChapter;
    }

    // Get a specific book object based off of its name
    public Book getBook(String bookName){
        Book returnBook = new Book();
        for(Book book : mBooks)
            if(book.getBookName().equals(bookName))
                returnBook = book;
        return returnBook;
    }

    // Gets all of the books in the collection
    public ArrayList<Book> getBooks(){return mBooks;}
}
