package awhiting18.flashcardpro;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by awhiting18 on 11/24/2015.
 */
public class Deck {
    private String mSubjectName;
    private ArrayList<Card> mCards = new ArrayList<>();

    public Deck(String subjectName){ this.mSubjectName = subjectName; }
    public Deck(String subjectName, ArrayList<Card> cards){
        this.mCards = cards;
        this.mSubjectName = subjectName;
    }

    // Get methods
    public String getSubjectName() {return this.mSubjectName;}
    public ArrayList<Card> getCards(){ return this.mCards;}

    // Set methods
    public void setSubjectName(String subjectName){this.mSubjectName = subjectName;}
    public void setCards(ArrayList<Card> cards){this.mCards = cards;}

    public Card getCard(UUID cardId){
        for(Card card : mCards)
            if(card.getId() == cardId)
                return card;
        return null;
    }
}
