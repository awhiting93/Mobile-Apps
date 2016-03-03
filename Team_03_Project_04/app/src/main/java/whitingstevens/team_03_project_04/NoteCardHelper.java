package whitingstevens.team_03_project_04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 121667 on 12/3/2015.
 */
public class NoteCardHelper extends SQLiteOpenHelper{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "noteCard.db";

    public NoteCardHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + NoteCardDbSchema.FolderTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        NoteCardDbSchema.FolderTable.Cols.UUID + ", " +
                        NoteCardDbSchema.FolderTable.Cols.FOLDER_NAME + ")"
        );
        db.execSQL("create table " + NoteCardDbSchema.DeckTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        NoteCardDbSchema.DeckTable.Cols.UUID + ", " +
                        NoteCardDbSchema.DeckTable.Cols.DECK_NAME + ", " +
                        NoteCardDbSchema.DeckTable.Cols.FOLDER_UUID + ")"
        );
        db.execSQL("create table " + NoteCardDbSchema.CardTable.NAME + "(" +
                        " _id integer primary key autoincrement, " +
                        NoteCardDbSchema.CardTable.Cols.UUID + ", " +
                        NoteCardDbSchema.CardTable.Cols.FRONT_TEXT + ", " +
                        NoteCardDbSchema.CardTable.Cols.BACK_TEXT + ", " +
                        NoteCardDbSchema.CardTable.Cols.DATE_CREATED + ", " +
                        NoteCardDbSchema.CardTable.Cols.DECK_UUID + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
