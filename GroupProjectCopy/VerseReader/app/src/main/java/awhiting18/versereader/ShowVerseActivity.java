// Programmer Name: Andrew Whiting
// Date: 11/4/15
// Project Name: Verse Reader
// File Description: Activity from which to launch a verse list

package awhiting18.versereader;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;


public class ShowVerseActivity extends FragmentActivity {
    FragmentManager mVerseManager;
    TextView        mBookTitle;
    TextView        mChapterInfo;
    String          mBookName;
    int             mChapterNumber;
    ViewPager       mVp;
    List<Chapter>   mChapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_verse);

        // Determine Chapter information to update view
        mBookName = getIntent().getStringExtra("bookName");
        mChapterNumber = getIntent().getIntExtra("chapterNumber", 1);
        mBookTitle = (TextView)findViewById(R.id.chapterNumberTitle);
        mChapterInfo = (TextView)findViewById(R.id.selectAChapter);

        // Update book title
        mBookTitle.setText(mBookName);

        // Get the books
        mChapters = BookSingleton.get().getBook(mBookName).getChapters();
        mVerseManager = getSupportFragmentManager();

        // Set up ViewPager adapter
        mVp = (ViewPager)findViewById(R.id.verseFragmentHolder);
        mVp.setAdapter(new FragmentStatePagerAdapter(mVerseManager) {
            @Override
            public Fragment getItem(int position) {
                Fragment verseListFragment = new VerseListFragment();

                Bundle args = new Bundle();
                Chapter chapter = mChapters.get(position);
                args.putString("bookName", chapter.getBookName());
                args.putInt("chapterNumber", chapter.getChapterNumber());

                verseListFragment.setArguments(args);
                return verseListFragment;
            }

            @Override
            public int getCount() {
                return mChapters.size();
            }
        });

        for(int i = 0; i < mChapters.size(); i++){
            if(mChapters.get(i).getBookName().equals(getIntent().getStringExtra("bookName")) &&
                    mChapters.get(i).getChapterNumber() == getIntent().getIntExtra("chapterNumber", 0)) {
                mVp.setCurrentItem(i);
                break;
            }
        }

    }

    public void openBook(FragmentManager mVerseManager, Fragment verseFragment){
        if(verseFragment == null){
            verseFragment = new VerseListFragment();

            verseFragment.setArguments(getIntent().getExtras());
            mVerseManager.beginTransaction().add(R.id.verseFragmentHolder, verseFragment).commit();
        }
    }
}
