package program03.databaseexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mText;
    CrimeSingleton mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCrimes = CrimeSingleton.get(this);

        mText = (TextView)findViewById(R.id.myText);
        mText.setText(mCrimes.showCrimes());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_add:
                Crime crime = new Crime();
                crime.setTitle("Crime #" + (mCrimes.getCrimes().size() + 1));
                crime.setSolved(false);
                mCrimes.addCrime(crime);
                mText.setText(mCrimes.showCrimes());
                return true;

            case R.id.action_del:
                if (mCrimes.getCrimes().size() - 1 >= 0){
                    mCrimes.deleteCrime(mCrimes.getCrimes().get(mCrimes.getCrimes().size() - 1));
                    mText.setText(mCrimes.showCrimes());
                }
                return true;

            default:
                    return super.onOptionsItemSelected(item);
        }

    }

}
