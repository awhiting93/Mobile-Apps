package awhiting18.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by awhiting18 on 10/23/2015.
 */
public class CrimeSingleton {
    private static CrimeSingleton mCrimeLab;
    private ArrayList<Crime> mCrimes;

    private CrimeSingleton(){
        mCrimes = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Crime c = new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i % 2 == 1);
            mCrimes.add(c);
        }
    }
    public static CrimeSingleton get(){
        if(mCrimeLab == null)
            mCrimeLab = new CrimeSingleton();
        return mCrimeLab;
    }

    public List<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime crime : mCrimes)
            if(crime.getId().equals(id))
                return crime;

        return null;
    }
}
