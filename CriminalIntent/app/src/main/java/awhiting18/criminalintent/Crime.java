package awhiting18.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by awhiting18 on 10/19/2015.
 */
public class Crime {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date(); 
    }

    // Getters and Setters
    public boolean isSolved() {return mSolved;}
    public void setSolved(boolean solved) {mSolved = solved;}
    public Date getDate() {return mDate;}
    public void setDate(Date mDate) {this.mDate = mDate;}
    public UUID getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String mTitle) {this.mTitle = mTitle;}

}
