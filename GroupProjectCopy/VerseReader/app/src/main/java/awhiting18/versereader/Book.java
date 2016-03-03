// Programmer Name: Andrew Whiting
// Date: 11/4/15
// Project Name: Verse Reader
// File Description: Defines a book class. A book class contains a collection of chapters

package awhiting18.versereader;

import java.util.ArrayList;

public class Book {
    private String             mBookName;
    private int                mChapterNumber = 1;
    private ArrayList<Chapter> mChapters = new ArrayList<>();

    // Getters and Setters
    public ArrayList<Chapter> getChapters()                 {return mChapters;}
    public String             getBookName()                 {return mBookName;}
    public void               setBookName(String mBookName) {this.mBookName = mBookName;}


    // Create an empty book with no name
    public Book(){
        mBookName = "";
    }

    // Create a book using a collection of chapters and a book name
    public Book(ArrayList<String[]> chapters, String bookName){
        mBookName = bookName;
        for(String[] chapter : chapters) {
            mChapters.add(new Chapter(mChapterNumber, mBookName, chapter));
            mChapterNumber++;
        }
    }

    // Gets a specific verse based off of a chapter number and verse number
    public String getVerse(int chapterNumber, int verseNumber){
        String verseText = mBookName + " " + chapterNumber + ":" + verseNumber + " does not exist.";
        for(Chapter chapter : mChapters)
            if(chapter.getChapterNumber() == chapterNumber)
                for(Verse verse : chapter.getVerses())
                    if(verse.getVerseNumber() == verseNumber)
                        verseText = mBookName + " " + chapter.getChapterNumber() + ":" +
                        verse.getVerseNumber() + " - " + verse.getVerseText();
        return verseText;
    }
}
