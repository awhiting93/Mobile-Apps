package whitingstevens.team_03_project_04;

import android.content.ContentValues;
import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by 121667 on 11/28/2015.
 */
public class Folder{
    public static final int MAX_TITLE_LENGTH = 40;
    private String mFolderName;
    private ArrayList<Deck> mDecks = new ArrayList<>();
    private UUID mId;

    public Folder(){
        Deck d = new Deck();
        mDecks.add(d);
        this.mId = UUID.randomUUID();
    }
    public Folder(String folderName){
        this.mFolderName = folderName;
        this.mId = UUID.randomUUID();
    }

    public Folder(UUID id){
        this.mId = id;
    }

    public Folder(String folderName, ArrayList<Deck> decks){
        this.mFolderName = folderName;
        this.mDecks = decks;
        this.mId = UUID.randomUUID();
    }

    //Get methods
    public String getFolderName() {return this.mFolderName;}
    public ArrayList<Deck> getDecks() {return this.mDecks;}
    public UUID getId() {return this.mId;}

    //Set methods
    public void setFolderName(String folderName){this.mFolderName = folderName;}
    public void setDecks(ArrayList<Deck> decks){this.mDecks = decks;}

    // Get a specific deck
    public Deck getDeck(UUID deckId){
        for(Deck deck : mDecks)
            if(deck.getId().equals(deckId))
                return deck;
        return null;
    }
}
