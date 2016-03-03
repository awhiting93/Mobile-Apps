package program03.databaseexample;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import program03.databaseexample.CrimeSchema.CrimeTable;


/**
 * Created by jsparks on 10/23/2015.
 */
public class CrimeSingleton {
    private static CrimeSingleton mCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CrimeSingleton(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
//        for(int x = 0; x < 10; x++)
//        {
//            Crime crime = new Crime();
//            crime.setSolved(x % 2 == 0);
//            crime.setTitle("Crime #" + (x + 1));
//            mCrimes.add(crime);
//        }

    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );

        try{
            if(cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getID().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);

        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);

        return new CrimeCursorWrapper(cursor);
    }

    public static CrimeSingleton get(Context x) {
        if (mCrimeLab == null) {
            mCrimeLab = new CrimeSingleton(x);
        }
        return mCrimeLab;
    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public String showCrimes() {
        String x = "";

        for (Crime crime : getCrimes()) {
            x += crime.getTitle() + "\n";
        }
        return x;
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getID().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?", new String[]{uuidString});
    }

    public void addCrime(Crime c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    public void deleteCrime(Crime c){
        String uuidString = c.getID().toString();

        mDatabase.delete(CrimeTable.NAME, CrimeTable.Cols.UUID + " = ?", new String[] {uuidString});
    }
}
