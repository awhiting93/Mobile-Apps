package program03.databaseexample;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jsparks on 10/19/2015.
 */
public class Crime {
    private UUID mID;
    private String mTitle;
    private Date mDate;
    private Boolean mSolved;

    public Boolean isSolved() {
        return mSolved;
    }

    public void setSolved(Boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Crime(){
        this(UUID.randomUUID());
    }

    public Crime(UUID id)
    {
        mID = id;
        mDate = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public UUID getID() {
        return mID;
    }
}
