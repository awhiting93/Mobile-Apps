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
public class CardListFragment extends Fragment{

    RecyclerView mView;
    CardAdapter mAdapter;
    FoldersSingleton mFoldersSingleton;
    ArrayList<Card> mCards;
    UUID mFolderId,
         mDeckId;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);
    }

    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle Sis)
    {
        View v = li.inflate(R.layout.card_list_fragment, vg, false);
        mView = (RecyclerView)v.findViewById(R.id.cardList);
        mView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFoldersSingleton = FoldersSingleton.get(getActivity().getApplicationContext());
        mFolderId = (UUID)getArguments().getSerializable("folderId");
        mDeckId   = (UUID)getArguments().getSerializable("deckId");
        mCards = mFoldersSingleton.getFolder(mFolderId).getDeck(mDeckId).getCards();
        mAdapter = new CardAdapter(mCards);
        mView.setAdapter(mAdapter);
        return v;
    }

    public class CardHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        Card mCard;
        private TextView mCardName;
        private Button mDeleteButton;

        @Override
        public void onClick(View v){
            Intent i = new Intent(getActivity(), ShowCardActivity.class);
            i.putExtra("deckName", mFoldersSingleton.getFolder(mFolderId).getDeck(mDeckId).getDeckName());
            i.putExtra("cardId", mCard.getId());
            i.putExtra("deckId", mDeckId);
            i.putExtra("folderId", mFolderId);
            startActivity(i);
        }

        public CardHolder(View item){
            super(item);
            item.setOnClickListener(this);
            mCardName = (TextView)item.findViewById(R.id.cardTitle);
            mDeleteButton = (Button)item.findViewById(R.id.deleteCardButton);
        }

        public void bindFolder(Card d) {
            mCard = d;
            if(d.getFrontSideText().length() > Card.MAX_TITLE_LENGTH)
                mCardName.setText(d.getFrontSideText().substring(0, Card.MAX_TITLE_LENGTH - 1) + "...");
            else
                mCardName.setText(d.getFrontSideText());

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FoldersSingleton.get().deleteCard(mCard, mDeckId, mFolderId);
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public class CardAdapter extends RecyclerView.Adapter<CardHolder>{
        ArrayList<Card> mCards;

        public CardAdapter(ArrayList<Card> Cards) {mCards = Cards;}

        @Override
        public CardHolder onCreateViewHolder(ViewGroup vg, int type){
            LayoutInflater li = LayoutInflater.from(getActivity());
            View v = li.inflate(R.layout.list_item_card, vg, false);
            Log.d("test", "inflate Card holder");
            return new CardHolder(v);
        }

        @Override
        public void onBindViewHolder(CardHolder ch, int position){
            Card Card = mCards.get(position);
            ch.bindFolder(Card);
        }

        @Override
        public int getItemCount(){return mCards.size();}
    }

    @Override
    public void onResume()
    {
        FoldersSingleton lab = FoldersSingleton.get();
        ArrayList<Card> mCard = lab.getFolder(mFolderId).getDeck(mDeckId).getCards();
        super.onResume();
        if(mAdapter == null)
        {
            mAdapter = new CardAdapter(mCard);
        }
        else
        {
            mAdapter.notifyDataSetChanged();
        }

    }
}
