package whitingstevens.team_03_project_04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by 121667 on 11/28/2015.
 */
public class DeckListFragment extends Fragment{

    RecyclerView mView;
    DeckAdapter mAdapter;
    FoldersSingleton mFoldersSingleton;
    ArrayList<Deck> mDecks;
    UUID mFolderId;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);
    }

    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle Sis)
    {
        View v = li.inflate(R.layout.deck_list_fragment, vg, false);
        mView = (RecyclerView)v.findViewById(R.id.deckList);
        mView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFoldersSingleton = FoldersSingleton.get(getActivity().getApplicationContext());
        mFolderId = (UUID)getArguments().getSerializable("folderId");
        mDecks = mFoldersSingleton.getFolder(mFolderId).getDecks();
        mAdapter = new DeckAdapter(mDecks);
        mView.setAdapter(mAdapter);
        return v;
    }

    public class DeckHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        Deck mDeck;
        private TextView mDeckName;
        private Button mDeleteButton;

        @Override
        public void onClick(View v){
            Intent i = new Intent(getActivity(), SelectCardsActivity.class);
            i.putExtra("deckName", mDeck.getDeckName());
            i.putExtra("deckId", mDeck.getId());
            i.putExtra("folderId", mFolderId);
            startActivity(i);
        }

        public DeckHolder(View item){
            super(item);
            item.setOnClickListener(this);
            mDeckName = (TextView)item.findViewById(R.id.deckTitle);
            mDeleteButton = (Button)item.findViewById(R.id.deleteButton2);
        }

        public void bindFolder(Deck d) {
            mDeck = d;
            mDeckName.setText(d.getDeckName());

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FoldersSingleton.get().deleteDeck(mDeck, mFolderId);
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public class DeckAdapter extends RecyclerView.Adapter<DeckHolder>{
        ArrayList<Deck> mDecks;

        public DeckAdapter(ArrayList<Deck> decks) {mDecks = decks;}

        @Override
        public DeckHolder onCreateViewHolder(ViewGroup vg, int type){
            LayoutInflater li = LayoutInflater.from(getActivity());
            View v = li.inflate(R.layout.list_item_deck, vg, false);
            Log.d("test", "inflate deck holder");
            return new DeckHolder(v);
        }

        @Override
        public void onBindViewHolder(DeckHolder ch, int position){
            Deck deck = mDecks.get(position);
            ch.bindFolder(deck);
        }

        @Override
        public int getItemCount(){return mDecks.size();}
    }

    @Override
    public void onResume()
    {
        FoldersSingleton lab = FoldersSingleton.get();
        ArrayList<Deck> mDeck = lab.getFolder(mFolderId).getDecks();
        super.onResume();
        if(mAdapter == null)
        {
            mAdapter = new DeckAdapter(mDeck);
        }
        else
        {
            mAdapter.notifyDataSetChanged();
        }

    }
}
