package awhiting18.criminalintent;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.UUID;


public class CrimeActivity extends FragmentActivity {

    ViewPager mVp;
    List<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_activity_crime);

        mCrimes = CrimeSingleton.get().getCrimes();
        FragmentManager manager = getSupportFragmentManager();
        mVp = (ViewPager)findViewById(R.id.fragment_container2);
        mVp.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                Fragment myFragment = new CrimeFragment();

                Bundle args = new Bundle();
                Crime c = mCrimes.get(position);
                args.putSerializable("myCrime", c.getId());

                myFragment.setArguments(args);
                return myFragment;
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for(int i = 0; i < mCrimes.size(); i++){
            if(mCrimes.get(i).getId().equals((UUID)getIntent().getSerializableExtra("myCrime"))){
                mVp.setCurrentItem(i);
                break;
            }
        }

//        Fragment myFragment = manager.findFragmentById(R.id.fragment_container);
//
//        if(myFragment == null)
//        {
//            myFragment = new CrimeFragment();
//
//            Bundle args = new Bundle();
//            args.putSerializable("mycrime", (UUID)getIntent().getSerializableExtra("mycrime"));
//
//            myFragment.setArguments(args);
//            manager.beginTransaction().add(R.id.fragment_container,
//                    myFragment).commit();
//
//        }
    }
}
