package whitingstevens.team_03_project_04;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 121667 on 11/28/2015.
 */
public class FoldersSingleton {
    private static FoldersSingleton mFoldersSingleton;
    private ArrayList<Folder> mFolders = new ArrayList<>();
    private static Context mContext;
    private SQLiteDatabase mDatabase;
    public Cursor cursor;

    // Constructor, create a folderSingleton
    private FoldersSingleton(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new NoteCardHelper(context).getWritableDatabase();
        if((this.mFolders = getDBFolders()).size() == 0) {
            Folder tempFolder = addFolder(new Folder("New Folder"));
            Deck tempDeck = addDeck(new Deck("New Deck"), tempFolder.getId());
            addCard(new Card("New Card"), tempDeck.getId(), tempFolder.getId());
        }
    }

    // Create a new singleton if one does not exist, otherwise return the current one
    public static FoldersSingleton get(Context context)
    {
        if(mFoldersSingleton == null)
            mFoldersSingleton = new FoldersSingleton(context);
        return mFoldersSingleton;
    }

    public static FoldersSingleton get()
    {
        if(mFoldersSingleton == null)
            mFoldersSingleton = new FoldersSingleton(mContext);
        return mFoldersSingleton;
    }

    // Get the list of folders
    public ArrayList<Folder> getFolders(){return mFolders;}
    public Folder getFolder(UUID folderId){
        for(Folder folder : mFolders)
            if(folder.getId().equals(folderId))
                return folder;
        return null;
    }

    // Get a folder's values for the database
    private static ContentValues getFolderValues(Folder folder){
        ContentValues values = new ContentValues();
        values.put(NoteCardDbSchema.FolderTable.Cols.UUID, folder.getId().toString());
        values.put(NoteCardDbSchema.FolderTable.Cols.FOLDER_NAME, folder.getFolderName());

        return values;
    }

    // Add a folder to the database
    public Folder addFolder(Folder f){
        ContentValues values = getFolderValues(f);
        mFolders.add(f);
        mDatabase.insert(NoteCardDbSchema.FolderTable.NAME, null, values);
        return f;
    }

    // Update a folder in the database
    public void updateFolder(Folder f){
        int folderIndex = mFolders.indexOf(f);

        String uuidString = f.getId().toString();
        ContentValues values = getFolderValues(f);

        mFolders.remove(f);
        mFolders.add(folderIndex, f);

        mDatabase.update(NoteCardDbSchema.FolderTable.NAME, values,
                NoteCardDbSchema.FolderTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    // Delete a folder from the database
    public void deleteFolder(Folder f){
        String uuidString = f.getId().toString();
        mFolders.remove(f);
        mDatabase.delete(NoteCardDbSchema.FolderTable.NAME, null, new String[]{uuidString});
    }

    //Get a deck's values for the database
    private static ContentValues getDeckValues(Deck deck, UUID folderId){
        ContentValues values = new ContentValues();
        values.put(NoteCardDbSchema.DeckTable.Cols.DECK_NAME, deck.getDeckName());
        values.put(NoteCardDbSchema.DeckTable.Cols.FOLDER_UUID, folderId.toString());
        values.put(NoteCardDbSchema.DeckTable.Cols.UUID, deck.getId().toString());

        return values;
    }

    // Add a deck to the database
    public Deck addDeck(Deck d, UUID folderId){
        ContentValues values = getDeckValues(d, folderId);

        this.getFolder(folderId).getDecks().add(d);
        mDatabase.insert(NoteCardDbSchema.DeckTable.NAME, null, values);
        return d;
    }

    // Update a deck in the database
    public void updateDeck(Deck d, UUID folderId){
        int deckIndex = this.getFolder(folderId).getDecks().indexOf(d);
        String uuidString = d.getId().toString();
        ContentValues values = getDeckValues(d, folderId);

        this.getFolder(folderId).getDecks().remove(d);
        this.getFolder(folderId).getDecks().add(deckIndex, d);

        mDatabase.update(NoteCardDbSchema.DeckTable.NAME, values,
                NoteCardDbSchema.DeckTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    // Delete a deck from the database
    public void deleteDeck(Deck d, UUID folderId){
        String uuidString = d.getId().toString();

        this.getFolder(folderId).getDecks().remove(d);

        mDatabase.delete(NoteCardDbSchema.DeckTable.NAME, null, new String[]{uuidString});
    }

    // Get the card's values for the database
    private static ContentValues getCardValues(Card card, UUID deckId){
        ContentValues values = new ContentValues();
        values.put(NoteCardDbSchema.CardTable.Cols.DATE_CREATED, card.getDateCreated().toString());
        values.put(NoteCardDbSchema.CardTable.Cols.FRONT_TEXT, card.getFrontSideText());
        values.put(NoteCardDbSchema.CardTable.Cols.UUID, card.getId().toString());
        values.put(NoteCardDbSchema.CardTable.Cols.DECK_UUID, deckId.toString());
        values.put(NoteCardDbSchema.CardTable.Cols.BACK_TEXT, card.getBackSideText());

        return values;
    }

    // Add a card to the database
    public void addCard(Card c, UUID deckId, UUID folderId){
        ContentValues values = getCardValues(c, deckId);
        c.setDateCreated(new Date());
        this.getFolder(folderId).getDeck(deckId).getCards().add(c);
        mDatabase.insert(NoteCardDbSchema.CardTable.NAME, null, values);
    }

    // Update a Card in the database
    public void updateCard(Card c, UUID deckId, UUID folderId){
        int cardIndex = this.getFolder(folderId).getDeck(deckId).getCards().indexOf(c);
        String uuidString = c.getId().toString();
        ContentValues values = getCardValues(c, deckId);

        this.getFolder(folderId).getDeck(deckId).getCards().remove(c);
        this.getFolder(folderId).getDeck(deckId).getCards().add(cardIndex, c);

        mDatabase.update(NoteCardDbSchema.CardTable.NAME, values,
                NoteCardDbSchema.CardTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    // Delete a Card from the database
    public void deleteCard(Card c, UUID deckId, UUID folderId){
        String uuidString = c.getId().toString();

        this.getFolder(folderId).getDeck(deckId).getCards().remove(c);

        mDatabase.delete(NoteCardDbSchema.CardTable.NAME, null, new String[]{uuidString});
    }

    // Querying for folders
    private DataCursorWrapper queryFolders(String whereClause, String[] whereArgs){
            cursor = mDatabase.query(
                NoteCardDbSchema.FolderTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new DataCursorWrapper(cursor);
    }

    // Querying for folders
    private DataCursorWrapper queryDecks(String whereClause, String[] whereArgs){
            cursor = mDatabase.query(
                NoteCardDbSchema.DeckTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new DataCursorWrapper(cursor);
    }

    // Querying for folders
    private DataCursorWrapper queryCards(String whereClause, String[] whereArgs){
            cursor = mDatabase.query(
                NoteCardDbSchema.CardTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new DataCursorWrapper(cursor);
    }

    public ArrayList<Folder> getDBFolders(){
        ArrayList<Folder> folders = new ArrayList<>();
        ArrayList<Deck> decks = new ArrayList<>();
        ArrayList<Card> cards = new ArrayList<>();
        Folder folder;
        Deck deck;

        DataCursorWrapper folderCursor = queryFolders(null, null);
        DataCursorWrapper deckCursor = null;
        DataCursorWrapper cardCursor = null;

        try{
            folderCursor.moveToFirst();
            while(!folderCursor.isAfterLast()){
                folder = folderCursor.getFolder();
                folders.add(folder);
                deckCursor = queryDecks(NoteCardDbSchema.DeckTable.Cols.FOLDER_UUID + " = ?", new String[]{folderCursor.getFolder().getId().toString()});
                try {
                    Log.d("LoadDB", "before load");
                    deckCursor.moveToFirst();
                    while (!deckCursor.isAfterLast()) {
                        Log.d("LoadDB", "found a deck!");
                        deck = deckCursor.getDeck();
                        decks.add(deck);
                        cardCursor = queryCards(NoteCardDbSchema.CardTable.Cols.DECK_UUID + " = ?", new String[]{deckCursor.getDeck().getId().toString()});
                        try {
                            cardCursor.moveToFirst();
                            while (!cardCursor.isAfterLast()) {
                                Log.d("LoadDB", "found a card!");
                                cards.add(cardCursor.getCard());
                                cardCursor.moveToNext();
                            }
                        deckCursor.moveToNext();
                        } finally{
                            deck.setCards(cards);
                            cards = new ArrayList<>();
                            cardCursor.close();
                        }
                    }
                } finally{
                    folder.setDecks(decks);
                    decks = new ArrayList<>();
                    deckCursor.close();
                }
                folderCursor.moveToNext();
            }
        } finally{
            folderCursor.close();
        }

        return folders;
    }
}
