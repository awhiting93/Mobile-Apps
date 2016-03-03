// Programmer Name: Andrew Whiting
// Date: 11/4/15
// Project Name: Verse Reader
// File Description: Activity from which to launch a chapter list

package awhiting18.versereader;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class VerseReaderActivity extends FragmentActivity {
    FragmentManager mChapterManager;

    Fragment mFJohnFragment,
             mSJohnFragment,
             mTJohnFragment;
    TextView mBookTitle,
             mInfoBar;
    String   mBookName;
    Button   mButtonFJohn,
             mButtonSJohn,
             mButtonTJohn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verse_reader);

        ArrayList<String[]> firstJohn  = new ArrayList<>();
        ArrayList<String[]> secondJohn = new ArrayList<>();
        ArrayList<String[]> thirdJohn  = new ArrayList<>();

        // Add chapters of First John
        firstJohn.add(getResources().getStringArray(R.array.first_john_1));
        firstJohn.add(getResources().getStringArray(R.array.first_john_2));
        firstJohn.add(getResources().getStringArray(R.array.first_john_3));
        firstJohn.add(getResources().getStringArray(R.array.first_john_4));
        firstJohn.add(getResources().getStringArray(R.array.first_john_5));

        // Add chapters of Second John
        secondJohn.add(getResources().getStringArray(R.array.second_john));

        // Add chapters of Third John
        thirdJohn.add(getResources().getStringArray(R.array.third_john));

        // Instantiate Bible
        BookSingleton Bible = BookSingleton.get();
        Bible.addBook(firstJohn, getResources().getString(R.string.first_john));
        Bible.addBook(secondJohn, getResources().getString(R.string.second_john));
        Bible.addBook(thirdJohn, getResources().getString(R.string.third_john));

        mBookTitle = (TextView)findViewById(R.id.bookNameTitle);
        mInfoBar  = (TextView)findViewById(R.id.selectAChapter);
        mButtonFJohn = (Button)findViewById(R.id.buttonFJohn);
        mButtonSJohn = (Button)findViewById(R.id.buttonSJohn);
        mButtonTJohn = (Button)findViewById(R.id.buttonTJohn);

        // First John Button Click Listener
        mButtonFJohn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookName = getResources().getString(R.string.first_john);
                updateView(mBookName);
            }
        });

        // Second John Button Click Listener
        mButtonSJohn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookName = getResources().getString(R.string.second_john);
                updateView(mBookName);
            }
        });

        // Third John Button Click Listener
        mButtonTJohn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookName = getResources().getString(R.string.third_john);
                updateView(mBookName);
            }
        });

    }

    // Opens the book, revealing the chapters
    public void openBook(FragmentManager chapterManager, Fragment chapterFragment){
        if(chapterFragment == null){
            chapterFragment = new ChapterListFragment();

            chapterFragment.setArguments(getIntent().getExtras());
            chapterManager.beginTransaction().add(R.id.chapterFragmentHolder, chapterFragment).commit();
        }
        else{
            chapterFragment = new ChapterListFragment();
            chapterFragment.setArguments(getIntent().getExtras());
            chapterManager.beginTransaction().replace(R.id.chapterFragmentHolder, chapterFragment).commit();
        }
    }

    // Updates all of the items on the screen based on current information
    public void updateView(String bookName){
        // Set text views
        mBookTitle.setText(bookName);
        getIntent().putExtra("bookName", bookName);
        mInfoBar.setText(getResources().getString(R.string.select_a_chapter));

        // Instantiate fragment manager
        mChapterManager = getSupportFragmentManager();

        // Load appropriate fragment based off of the book name
        if(bookName.equals(getResources().getString(R.string.first_john))) {
            mFJohnFragment = mChapterManager.findFragmentById(R.id.chapterFragmentHolder);
            openBook(mChapterManager, mFJohnFragment);
        }
        else if(bookName.equals(getResources().getString(R.string.second_john))) {
            mSJohnFragment = mChapterManager.findFragmentById(R.id.chapterFragmentHolder);
            openBook(mChapterManager, mSJohnFragment);
        }
        else if(bookName.equals(getResources().getString(R.string.third_john))) {
            mTJohnFragment = mChapterManager.findFragmentById(R.id.chapterFragmentHolder);
            openBook(mChapterManager, mTJohnFragment);
        }
    }
}
