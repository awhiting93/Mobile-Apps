package awhiting18.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jsparks on 10/28/2015.
 */
public class CrimeListFragment extends Fragment {
    RecyclerView myView;
    CrimeAdapter mAd;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);

        Log.d("Test", "Fragment onCreate " );
    }

    public View onCreateView(LayoutInflater li, ViewGroup vg,
                             Bundle sis)
    {
        Log.d("Test", "Fragment onCreateView ");
        View v = li.inflate(R.layout.crime_list_fragment, vg, false);
        myView = (RecyclerView)v.findViewById(R.id.myList);
        myView.setLayoutManager(new LinearLayoutManager(getActivity()));
        CrimeSingleton lab = CrimeSingleton.get();
        List<Crime> mCrimes = lab.getCrimes();

        mAd = new CrimeAdapter(mCrimes);
        myView.setAdapter(mAd);


        return v;

    }

    public class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        Crime myCrime;

        private TextView mText1;
        private TextView mText2;
        private CheckBox mCheck;

        @Override
        public void onClick(View v){
            Intent i = new Intent(getActivity(), CrimeActivity.class);
            i.putExtra("mycrime", myCrime.getId());
            startActivity(i);
        }

        public CrimeHolder(View item){
            super(item);
            item.setOnClickListener(this);
            mText1 = (TextView)item.findViewById(R.id.textView);
            mText2 = (TextView)item.findViewById(R.id.textView2);
            mCheck = (CheckBox)item.findViewById(R.id.checkBox);
        }

        public void bindCrime(Crime c){
            myCrime = c;
            mText1.setText(myCrime.getTitle());
            mText2.setText(myCrime.getDate().toString());
            mCheck.setChecked(myCrime.isSolved());
        }
    }

    public class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup vg, int type){

            LayoutInflater li = LayoutInflater.from(getActivity());
            View v = li.inflate(R.layout.list_item_crime, vg, false);
            return new CrimeHolder(v);
        }

        @Override
        public void onBindViewHolder(CrimeHolder ch, int position){
            Crime crime = mCrimes.get(position);
            ch.bindCrime(crime);

        }
        @Override
        public int getItemCount(){
            return mCrimes.size();
        }

    }

    @Override
    public void onResume()
    {
        CrimeSingleton lab = CrimeSingleton.get();
        List<Crime> mCrimes = lab.getCrimes();
        super.onResume();
        if(mAd == null)
        {
            mAd = new CrimeAdapter(mCrimes);
            myView.setAdapter(mAd);
        }
        else
        {
            mAd.notifyDataSetChanged();
        }
    }
}
