package whitingstevens.team_03_project_04;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by awhiting18 on 11/24/2015.
 */
public class Deck {
    public static final int MAX_TITLE_LENGTH = 30;
    private String mSubjectName;
    private ArrayList<Card> mCards = new ArrayList<>();
    private UUID mId;

    public Deck(){
        this.mId = UUID.randomUUID();
        this.mSubjectName = "Deck";
    }

    public Deck(UUID id){
        this.mId = id;
    }

    public Deck(String subjectName){
        this.mSubjectName = subjectName;
        this.mId = UUID.randomUUID();
    }
    public Deck(String subjectName, UUID folderId){
        this.mSubjectName = subjectName;
        this.mId = UUID.randomUUID();
        FoldersSingleton.get().addCard(new Card("New Card"), mId, folderId);
    }
    public Deck(String subjectName, ArrayList<Card> cards){
        this.mCards = cards;
        this.mSubjectName = subjectName;
        this.mId = UUID.randomUUID();
    }

    // Get methods
    public String getDeckName() {return this.mSubjectName;}
    public ArrayList<Card> getCards(){ return this.mCards;}
    public UUID getId(){ return this.mId; }

    // Set methods
    public void setSubjectName(String subjectName){this.mSubjectName = subjectName;}
    public void setCards(ArrayList<Card> cards){this.mCards = cards;}

    public Card getCard(UUID cardId){
        for(Card card : mCards)
            if(card.getId().equals(cardId))
                return card;
        return null;
    }
}
