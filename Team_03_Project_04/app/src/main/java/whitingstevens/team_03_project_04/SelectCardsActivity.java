package whitingstevens.team_03_project_04;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

public class SelectCardsActivity extends AppCompatActivity {

    UUID     mFolderId,
             mDeckId;
    EditText mDeckTitle;
    Deck     mDeck;
    EditText newItemTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cards);
        Log.d("SelectCardsActivity", "Made it!");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.cardFragmentHolder);

        mFolderId    = (UUID)getIntent().getSerializableExtra("folderId");
        mDeckId      = (UUID)getIntent().getSerializableExtra("deckId");
        mDeck        = FoldersSingleton.get(getApplicationContext()).getFolder(mFolderId).getDeck(mDeckId);
        for(Card card : mDeck.getCards())
            card.setOnFront(true);

        mDeckTitle = (EditText)findViewById(R.id.deckName);
        mDeckTitle.setText(mDeck.getDeckName());
        mDeckTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mDeck.setSubjectName(s.toString());
                FoldersSingleton.get(getApplicationContext()).updateDeck(mDeck, mFolderId);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(fragment == null){
            fragment = new CardListFragment();
            Bundle args = new Bundle();
            args.putSerializable("folderId", mFolderId);
            args.putSerializable("deckId", mDeckId);
            fragment.setArguments(args);
            fm.beginTransaction().add(R.id.cardFragmentHolder, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_cards, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.bob) {
            View v = LayoutInflater.from(this).inflate(R.layout.new_card_dialog_fragment, null);
            newItemTitle = (EditText)v.findViewById(R.id.editCardText);
            AlertDialog getNewItemTitle = new AlertDialog.Builder(this)
                    .setView(v)
                    .setTitle(R.string.new_card_text)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FoldersSingleton.get(getApplicationContext()).addCard(new Card(newItemTitle.getText().toString()), mDeckId, mFolderId);
                            Fragment fragment = new CardListFragment();
                            Bundle args = new Bundle();
                            args.putSerializable("folderId", mFolderId);
                            args.putSerializable("deckId", mDeckId);
                            fragment.setArguments(args);
                            FragmentManager fm = getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.cardFragmentHolder, fragment).commit();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .create();
            getNewItemTitle.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
