package awhiting18.flashcardpro;

import java.util.Date;
import java.util.UUID;

/**
 * Created by awhiting18 on 11/24/2015.
 */
public class Card {
    private String mBackSideText;
    private String mFrontSideText;
    private Date mDateCreated;
    private String mSubject;
    private boolean mStudied;
    private UUID mId;

    public Card(){
        this.mBackSideText = "";
        this.mFrontSideText = "";
        this.mDateCreated = new Date();
        this.mSubject = "";
        this.mStudied = false;
        this.mId = UUID.randomUUID();
    }
    public Card(String backSideText, String frontSideText, Date dateCreated,
                String subject){
        this.mBackSideText = backSideText;
        this.mFrontSideText = frontSideText;
        this.mDateCreated = dateCreated;
        this.mSubject = subject;
        this.mId = UUID.randomUUID();
    }

    // Get methods
    public String getBackSideText(){return this.mBackSideText;}
    public String getFrontSideText(){return this.mFrontSideText;}
    public Date getDateCreated(){return this.mDateCreated;}
    public String getSubject(){return this.mSubject;}
    public boolean isStudied(){return this.mStudied;}
    public UUID getId(){return this.mId;}

    // Set methods
    public void setBackSideText(String backSideText){this.mBackSideText = backSideText;}
    public void setFrontSideText(String frontSideText){this.mFrontSideText = frontSideText;}
    public void setDateCreated(Date dateCreated){this.mDateCreated = dateCreated;}
    public void setSubject(String subject){this.mSubject = subject;}
    public void setStudied(boolean studied){this.mStudied = studied;}
}
