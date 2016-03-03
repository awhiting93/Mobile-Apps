// Programmer Name: Andrew Whiting
// Date: 11/4/15
// Project Name: Verse Reader
// File Description: Defines a fragment containing a list of chapters
// and their important information

package awhiting18.versereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ChapterListFragment extends Fragment {
    RecyclerView   mBookView;
    ChapterAdapter mAd;
    String         mBookName;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);
    }

    public View onCreateView(LayoutInflater li, ViewGroup vg,
                             Bundle sis)
    {
        View v = li.inflate(R.layout.chapter_list_fragment, vg, false);
        mBookView = (RecyclerView)v.findViewById(R.id.chapterList);
        mBookView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Get the name of the book for the chapter list
        mBookName = getArguments().getString("bookName");

        // Get the library
        BookSingleton Bible = BookSingleton.get();

        mAd = new ChapterAdapter(Bible.getBook(mBookName).getChapters());
        mBookView.setAdapter(mAd);

        return v;

    }

    public class ChapterHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        private Chapter  mChapter;
        private TextView mChapterName,
                         mProgressText;
        private CheckBox mHasBeenRead;

        // Starts a verse list activity
        @Override
        public void onClick(View v){
            Intent i = new Intent(getActivity(), ShowVerseActivity.class);
            i.putExtra("bookName", mChapter.getBookName());
            i.putExtra("chapterNumber", mChapter.getChapterNumber());
            startActivity(i);
        }

        public ChapterHolder(View item){
            super(item);
            item.setOnClickListener(this);
            mChapterName = (TextView)item.findViewById(R.id.chapterName);
            mProgressText = (TextView)item.findViewById(R.id.progressText);
            mHasBeenRead = (CheckBox)item.findViewById(R.id.hasBeenRead);
        }

        public void bindChapter(Chapter chapter){
            mChapter = chapter;

            // Set fragment views
            mChapterName.setText("Chapter " + mChapter.getChapterNumber());
            mProgressText.setText(String.format("Current Progress: %.1f%%", chapter.calcProgress()));

            // If the chapter is checked, it has been completely read
            if(mHasBeenRead.isChecked())
                mProgressText.setText("Current Progress: 100.0%");

            // If the chapter progress is 100%, the chapter is completed
            if(chapter.calcProgress() == 100.0f)
                chapter.setRead(true);

            // Check the chapter off if it has been read and change background
            mHasBeenRead.setChecked(mChapter.hasBeenRead());
            if (mChapter.hasBeenRead()) {
                mChapterName.setBackgroundResource(R.drawable.has_been_read_background);
                mHasBeenRead.setBackgroundResource(R.drawable.has_been_read_background);
                mProgressText.setBackgroundResource(R.drawable.has_been_read_background);
            }
            //Set listener for check box
            mHasBeenRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mChapter.setRead(isChecked);

                    // If check box is not checked after it is changed, it has not been read
                    if(!isChecked)
                        mProgressText.setText("Current Progress: 0.0%");

                    getArguments().putString("bookName", mBookName);

                    // If chapter has been totally read, update progress and change background
                    if (mChapter.hasBeenRead()) {
                        mProgressText.setText("Current Progress: 100.0%");
                        mChapterName.setBackgroundResource(R.drawable.has_been_read_background);
                        mHasBeenRead.setBackgroundResource(R.drawable.has_been_read_background);
                        mProgressText.setBackgroundResource(R.drawable.has_been_read_background);
                    }
                    // If chapter has not been completed, revert to original background
                    else {
                        mChapterName.setBackgroundResource(R.drawable.fragment_background);
                        mHasBeenRead.setBackgroundResource(R.drawable.fragment_background);
                        mProgressText.setBackgroundResource(R.drawable.fragment_background);
                    }
                }
            });
        }
    }

    public class ChapterAdapter extends RecyclerView.Adapter<ChapterHolder>{
        ArrayList<Chapter> mChapters;

        public ChapterAdapter(ArrayList<Chapter> chapters){
            mChapters = chapters;
        }

        @Override
        public ChapterHolder onCreateViewHolder(ViewGroup vg, int type){

            LayoutInflater li = LayoutInflater.from(getActivity());
            View v = li.inflate(R.layout.list_item_chapter, vg, false);
            return new ChapterHolder(v);
        }

        @Override
        public void onBindViewHolder(ChapterHolder ch, int position){
            Chapter chapter = mChapters.get(position);
            ch.bindChapter(chapter);

        }
        @Override
        public int getItemCount(){
            return mChapters.size();
        }

    }

    @Override
    public void onResume()
    {
        BookSingleton Bible = BookSingleton.get();
        ArrayList<Chapter> mChapters = Bible.getBook(mBookName).getChapters();
        super.onResume();
        if(mAd == null)
        {
            mAd = new ChapterAdapter(mChapters);
            mBookView.setAdapter(mAd);
        }
        else
        {
            mAd.notifyDataSetChanged();
        }
    }
}
