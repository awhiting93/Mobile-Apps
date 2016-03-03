package awhiting18.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

/**
 * Created by awhiting18 on 10/19/2015.
 */
public class CrimeFragment extends Fragment {
    EditText mCrimeTitle;
    TextView mMyText;
    Button mDateButton;
    Crime mCrime;
    CheckBox mSolved;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);
        UUID id = (UUID)getArguments().getSerializable("mycrime");
        mCrime = CrimeSingleton.get().getCrime(id);
    }

    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle sis){
        View v = li.inflate(R.layout.fragment_crime, vg, false);
        mMyText = (TextView)v.findViewById(R.id.crimeTitleLabel);

        mCrimeTitle = (EditText)v.findViewById(R.id.crime_title);
        mCrimeTitle.setText(mCrime.getTitle());
        mCrimeTitle.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                mCrime.setTitle(s.toString());
                mMyText.setText(mCrime.getTitle());
            }

            @Override
            public void afterTextChanged(Editable s){

            }
        });

        mDateButton = (Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                Bundle bob = new Bundle();
                bob.putSerializable("fred", mCrime.getDate());
                DateFragment myDialog = new DateFragment();
                myDialog.setArguments(bob);
                myDialog.setTargetFragment(CrimeFragment.this, 0);
                myDialog.show(fm, "test");
            }
        });

        mSolved = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolved.setChecked(mCrime.isSolved());
        mSolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int request, int result, Intent sue){
        if(request == 0 && result == Activity.RESULT_OK){
            Date resultDate = (Date)sue.getSerializableExtra("newDate");
            mCrime.setDate(resultDate);
            mDateButton.setText(mCrime.getDate().toString());
        }
    }
}
