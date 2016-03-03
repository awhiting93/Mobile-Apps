package awhiting18.criminalintent;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class CrimeListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);

        FragmentManager manager = getSupportFragmentManager();
        Fragment myFragment = manager.findFragmentById(R.id.fragment_container);

        if(myFragment == null){
            myFragment = new CrimeListFragment();
            manager.beginTransaction().add(R.id.fragment_container, myFragment).commit();
        }
    }
}
