package whitingstevens.team_03_project_04;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class ShowCardActivity extends AppCompatActivity {
    ViewPager       mVp;
    Deck            mDeck;
    List<Card>      mCards;
    EditText        mDeckText;
    FragmentManager mVerseManager;
    UUID            mDeckId,
                    mFolderId,
                    mCardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card);

        Log.d("ShowCardActivity", "made it to on created");
        // Get the books
        mFolderId = (UUID)getIntent().getSerializableExtra("folderId");
        mDeckId   = (UUID)getIntent().getSerializableExtra("deckId");
        mCardId   = (UUID)getIntent().getSerializableExtra("cardId");
        mDeckText = (EditText)findViewById(R.id.deckName);
        mDeckText.clearFocus();

        mDeck = FoldersSingleton.get(getApplicationContext()).getFolder(mFolderId).getDeck(mDeckId);
        mCards = FoldersSingleton.get(getApplicationContext()).getFolder(mFolderId).getDeck(mDeckId).getCards();
        mVerseManager = getSupportFragmentManager();

        // Set up ViewPager adapter
        mVp = (ViewPager)findViewById(R.id.cardFragmentHolder);
        mVp.setAdapter(new FragmentStatePagerAdapter(mVerseManager) {
            @Override
            public Fragment getItem(int position) {
                Fragment cardFragment = new CardFragment();

                Bundle args = new Bundle();
                Card card = mCards.get(position);
                args.putSerializable("cardId", card.getId());
                args.putSerializable("deckId", mDeckId);
                args.putSerializable("folderId", mFolderId);

                cardFragment.setArguments(args);
                return cardFragment;
            }

            @Override
            public int getCount() {
                return mCards.size();
            }
        });

        for(int i = 0; i < mCards.size(); i++){
            if(mCards.get(i).getId().equals(mCardId)) {
                mVp.setCurrentItem(i);
                break;
            }
        }

        mDeckText.setText(mDeck.getDeckName());
        mDeckText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDeck.setSubjectName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_deck, menu);
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
}
