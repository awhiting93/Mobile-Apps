package whitingstevens.team_03_project_04;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by 121667 on 12/3/2015.
 */
public class DataCursorWrapper extends CursorWrapper {
    public DataCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Folder getFolder(){
        String uuidString = getString(getColumnIndex(NoteCardDbSchema.FolderTable.Cols.UUID));
        String name = getString(getColumnIndex(NoteCardDbSchema.FolderTable.Cols.FOLDER_NAME));

        Folder folder = new Folder(UUID.fromString(uuidString));
        folder.setFolderName(name);
        return folder;
    }

    public Deck getDeck(){
        String uuidString = getString(getColumnIndex(NoteCardDbSchema.DeckTable.Cols.UUID));
        String name = getString(getColumnIndex(NoteCardDbSchema.DeckTable.Cols.DECK_NAME));

        Deck deck = new Deck(UUID.fromString(uuidString));
        deck.setSubjectName(name);
        return deck;
    }

    public Card getCard(){
        String uuidString = getString(getColumnIndex(NoteCardDbSchema.CardTable.Cols.UUID));
        String frontText = getString(getColumnIndex(NoteCardDbSchema.CardTable.Cols.FRONT_TEXT));
        String backText = getString(getColumnIndex(NoteCardDbSchema.CardTable.Cols.BACK_TEXT));
        long dateCreated = getLong(getColumnIndex(NoteCardDbSchema.CardTable.Cols.DATE_CREATED));

        Card card = new Card(UUID.fromString(uuidString));
        card.setFrontSideText(frontText);
        card.setBackSideText(backText);
        card.setDateCreated(new Date(dateCreated));
        return card;
    }
}
