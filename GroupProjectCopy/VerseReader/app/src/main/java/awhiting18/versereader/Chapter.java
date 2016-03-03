// Programmer Name: Andrew Whiting
// Date: 11/4/15
// Project Name: Verse Reader
// File Description: Defines a chapter class. A book class contains a collection of verses

package awhiting18.versereader;

import java.util.ArrayList;
// Random comment
public class Chapter {
    private int              mChapterNumber;
    private int              mNumberOfVerses;
    private String           mBookName;
    private ArrayList<Verse> mVerses = new ArrayList<>();
    private Boolean          mRead = false;

    // Constructor: Creates a new chapter with no verses or associated book
    public Chapter(){
        mChapterNumber  = 0;
        mNumberOfVerses = 0;
        mBookName       = "";
        mVerses         = new ArrayList<>();
    }

    // Constructor: Creates a new chapter for a specific book, gives it a number,
    // and populates verses
    public Chapter(int chapterNumber, String bookName, String[] verses){
        mChapterNumber  = chapterNumber;
        mBookName       = bookName;
        mNumberOfVerses = verses.length;

        int verseNumber = 1;
        for(String verse : verses) {
            mVerses.add(new Verse(verseNumber, verse, chapterNumber, bookName));
            verseNumber++;
        }
    }

    // Setters and Getters
    public String getBookName() {return mBookName;}
    public int getChapterNumber() {return mChapterNumber;}
    public Boolean hasBeenRead() {return mRead;}
    public void setRead(Boolean hasBeenRead){mRead = hasBeenRead;}
    public ArrayList<Verse> getVerses(){return mVerses;}

    // Determine how many of the verses in this chapter have been read
    public float calcProgress(){
        int verseReadCounter = 0;
        for(Verse verse : mVerses){
            if(verse.hasBeenRead())
                verseReadCounter++;
        }
        return ((float)verseReadCounter / (float) this.mNumberOfVerses) * 100.0f;
    }
}
