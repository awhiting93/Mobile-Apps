// Programmer Name: Andrew Whiting
// Date: 11/4/15
// Project Name: Verse Reader
// File Description: Defines a verse class. A book class contains
// a verse text and information about that verse

package awhiting18.versereader;

public class Verse {
    private int     mChapterNumber;
    private int     mVerseNumber;
    private String  mVerseText;
    private String  mBookName;
    private Boolean mRead = false;

    // Constructor: Creates an empty verse
    public Verse(){
        mVerseNumber   = 0;
        mChapterNumber = 0;
        mVerseText     = "";
        mBookName      = "";
    }

    // Constructor: Creates a verse with all necessary information
    public Verse(int verseNumber, String verseText, int chapterNumber, String bookName){
        mVerseNumber   = verseNumber;
        mVerseText     = verseText;
        mChapterNumber = chapterNumber;
        mBookName      = bookName;
    }

    // Getters
    public int     getVerseNumber()  {return mVerseNumber;}
    public int     getChapterNumber(){return mChapterNumber;}
    public String  getVerseText()    {return mVerseText;}
    public String  getBookName()     {return mBookName;}
    public Boolean hasBeenRead()     {return mRead;}

    //Setters
    public void setVerseNumber(int verseNumber)    {mVerseNumber = verseNumber;}
    public void setVerseText(String verseText)     {mVerseText = verseText;}
    public void setChapterNumber(int chapterNumber){mChapterNumber = chapterNumber;}
    public void setBookName(String bookName)       {mBookName = bookName;}
    public void setRead(Boolean hasBeenRead)       {mRead = hasBeenRead;}
}
