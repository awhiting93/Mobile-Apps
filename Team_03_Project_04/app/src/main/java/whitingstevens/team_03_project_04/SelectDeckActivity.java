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

public class SelectDeckActivity extends AppCompatActivity {

    UUID mFolderId;
    String mFolderName;
    EditText mFolderTitle;
    Folder mFolder;
    EditText newItemTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_deck);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.deckFragmentHolder);
        mFolderTitle = (EditText)findViewById(R.id.folderName);
        mFolderId = (UUID)getIntent().getSerializableExtra("folderId");
        mFolderName = getIntent().getStringExtra("folderName");
        mFolder = FoldersSingleton.get(getApplicationContext()).getFolder(mFolderId);

        mFolderTitle = (EditText)findViewById(R.id.folderName);
        mFolderTitle.setText(mFolderName);
        mFolderTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mFolder.setFolderName(s.toString());
                FoldersSingleton.get(getApplicationContext()).updateFolder(mFolder);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if(fragment == null){
            fragment = new DeckListFragment();
            Bundle args = new Bundle();
            args.putSerializable("folderId", mFolderId);
            fragment.setArguments(args);
            Log.d("test", "set arguments");
            fm.beginTransaction().add(R.id.deckFragmentHolder, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_deck, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_deck) {
            View v = LayoutInflater.from(this).inflate(R.layout.new_item_dialog_fragment, null);
            newItemTitle = (EditText)v.findViewById(R.id.editTitle);
            AlertDialog getNewItemTitle = new AlertDialog.Builder(this)
                    .setView(v)
                    .setTitle(R.string.new_deck_title)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FoldersSingleton.get(getApplicationContext()).addDeck(new Deck(newItemTitle.getText().toString()), mFolderId );
                            Fragment fragment = new DeckListFragment();
                            Bundle args = new Bundle();
                            args.putSerializable("folderId", mFolderId);
                            fragment.setArguments(args);
                            FragmentManager fm = getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.deckFragmentHolder, fragment).commit();
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
