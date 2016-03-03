package awhiting18.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class StatsActivity extends ActionBarActivity {

    // TextViews for statistics
    TextView numberPresentText,
             numberAbsentText,
             numberTardyText,
             numberUnknownText,
             numberTotalText;
    Button   backButton;

    // Statistics to be displayed
    int      numberPresent,
             numberAbsent,
             numberTardy,
             numberUnknown,
             numberTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Associate TextViews with appropriate resources
        numberPresentText = (TextView)findViewById(R.id.numberPresent);
        numberAbsentText = (TextView)findViewById(R.id.numberAbsent);
        numberTardyText = (TextView)findViewById(R.id.numberTardy);
        numberUnknownText = (TextView)findViewById(R.id.numberUnknown);
        numberTotalText = (TextView)findViewById(R.id.numberTotal);
        backButton = (Button)findViewById(R.id.backButton);

        getStats();
        setResults();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setResults(){
        numberPresentText.setText(String.format("%2d (%.1f", numberPresent, ((float)numberPresent / (float)numberTotal) * 100.0f) + "%)");
        numberAbsentText.setText(String.format("%2d (%.1f", numberAbsent, ((float)numberAbsent / (float)numberTotal) * 100.0f) + "%)");
        numberTardyText.setText(String.format("%2d (%.1f", numberTardy, ((float)numberTardy / (float)numberTotal) * 100.0f) + "%)");
        numberUnknownText.setText(String.format("%2d (%.1f", numberUnknown, ((float)numberUnknown / (float)numberTotal) * 100.0f) + "%)");
        numberTotalText.setText(numberTotal + " (" + (((float)numberTotal / (float)numberTotal) * 100f) + "%)");
    }

    public void getStats(){
        numberPresent = getIntent().getExtras().getInt("numberPresent");
        numberAbsent  = getIntent().getExtras().getInt("numberAbsent");
        numberTardy   = getIntent().getExtras().getInt("numberTardy");
        numberUnknown = getIntent().getExtras().getInt("numberUnknown");
        numberTotal   = getIntent().getExtras().getInt("numberTotal");
    }
}
