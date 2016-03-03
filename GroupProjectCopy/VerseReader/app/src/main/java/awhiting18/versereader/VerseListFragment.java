// Programmer Name: Andrew Whiting
// Date: 11/4/15
// Project Name: Verse Reader
// File Description: Defines a fragment containing a list of verses

package awhiting18.versereader;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class VerseListFragment extends Fragment {
    RecyclerView mChapterView;
    VerseAdapter mAd;
    String       mBookName;
    int          mChapterNumber;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);
    }

    public View onCreateView(LayoutInflater li, ViewGroup vg,
                             Bundle sis)
    {
        View v = li.inflate(R.layout.chapter_list_fragment, vg, false);
        mChapterView = (RecyclerView)v.findViewById(R.id.chapterList);
        mChapterView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Determine originating book and chapter
        mBookName = getArguments().getString("bookName");
        mChapterNumber = getArguments().getInt("chapterNumber");

        // Get the library
        BookSingleton Bible = BookSingleton.get();

        mAd = new VerseAdapter(Bible.getChapter(mBookName, mChapterNumber).getVerses());
        mChapterView.setAdapter(mAd);


        return v;

    }

    public class VerseHolder extends RecyclerView.ViewHolder{
        private Verse    mVerse;
        private TextView mVerseText;

        public VerseHolder(View item){
            super(item);
            mVerseText = (TextView)item.findViewById(R.id.verseText);
        }

        public void bindVerse(Verse verse){
            mVerse = verse;
            mVerseText.setText(mVerse.getChapterNumber() + ":" + mVerse.getVerseNumber()+ " "
                    + mVerse.getVerseText());
            // Consider verse read once its fragment has loaded
            verse.setRead(true);
        }
    }

    public class VerseAdapter extends RecyclerView.Adapter<VerseHolder>{
        ArrayList<Verse> mVerses;

        public VerseAdapter(ArrayList<Verse> verses){
            mVerses = verses;
        }

        @Override
        public VerseHolder onCreateViewHolder(ViewGroup vg, int type){

            LayoutInflater li = LayoutInflater.from(getActivity());
            View v = li.inflate(R.layout.list_item_verse, vg, false);
            return new VerseHolder(v);
        }

        @Override
        public void onBindViewHolder(VerseHolder vh, int position){
            Verse verse = mVerses.get(position);
            vh.bindVerse(verse);

        }
        @Override
        public int getItemCount(){
            return mVerses.size();
        }

    }

    @Override
    public void onResume()
    {
        BookSingleton Bible = BookSingleton.get();
        ArrayList<Verse> mVerses = Bible.getChapter(mBookName, mChapterNumber).getVerses();
        super.onResume();
        if(mAd == null)
        {
            mAd = new VerseAdapter(mVerses);
            mChapterView.setAdapter(mAd);
        }
        else
        {
            mAd.notifyDataSetChanged();
        }
    }
}

