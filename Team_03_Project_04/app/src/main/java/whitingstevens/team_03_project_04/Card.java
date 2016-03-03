package whitingstevens.team_03_project_04;

import android.content.ContentValues;

import java.util.Date;
import java.util.UUID;

/**
 * Created by awhiting18 on 11/24/2015.
 */
public class Card {
    public static final int MAX_TITLE_LENGTH = 37;
    private String mBackSideText;
    private String mFrontSideText;
    private Date mDateCreated;
    private String mSubject;
    private boolean mStudied;
    private boolean mOnFront;
    private UUID mId;

    public Card(){
        this.mBackSideText = "Back";
        this.mFrontSideText = "Front";
        this.mDateCreated = new Date();
        this.mSubject = "Subject";
        this.mStudied = false;
        this.mId = UUID.randomUUID();
        this.mOnFront = true;
    }
    public Card(String frontSideText) {
        this.mBackSideText = "";
        this.mFrontSideText = frontSideText;
        this.mDateCreated = new Date();
        this.mSubject = "Subject";
        this.mStudied = false;
        this.mId = UUID.randomUUID();
        this.mOnFront = true;
    }

    public Card(UUID id){
        this.mId = id;
        this.mOnFront = true;
    }

    public Card(String frontSideText, String backSideText){
        this.mBackSideText = backSideText;
        this.mFrontSideText = frontSideText;
        this.mDateCreated = new Date();
        this.mSubject = "Subject";
        this.mStudied = false;
        this.mId = UUID.randomUUID();
        this.mOnFront = true;
    }
    public Card(String backSideText, String frontSideText, Date dateCreated,
                String subject){
        this.mBackSideText = backSideText;
        this.mFrontSideText = frontSideText;
        this.mDateCreated = dateCreated;
        this.mSubject = subject;
        this.mId = UUID.randomUUID();
        this.mOnFront = true;
    }

    // Get methods
    public String getBackSideText(){return this.mBackSideText;}
    public String getFrontSideText(){return this.mFrontSideText;}
    public Date getDateCreated(){return this.mDateCreated;}
    public String getSubject(){return this.mSubject;}
    public boolean isStudied(){return this.mStudied;}
    public UUID getId(){return this.mId;}
    public boolean isOnFront() {return this.mOnFront;}

    // Set methods
    public void setBackSideText(String backSideText){this.mBackSideText = backSideText;}
    public void setFrontSideText(String frontSideText){this.mFrontSideText = frontSideText;}
    public void setDateCreated(Date dateCreated){this.mDateCreated = dateCreated;}
    public void setSubject(String subject){this.mSubject = subject;}
    public void setStudied(boolean studied){this.mStudied = studied;}
    public void setOnFront(boolean OnFront){this.mOnFront = OnFront;}
}