package whitingstevens.team_03_project_04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by 121863 on 11/28/2015.
 */
public class CardFragment extends Fragment {
    Card mCard;
    ArrayList<Card> mCards;
    UUID mFolderId;
    UUID mDeckId;
    UUID mCardId;
    TextView mCardContent;
    TextView mCardSide;
    Button mEditCardButton;
    EditText mEditCard;
    boolean editModeEnabled = true;
    FoldersSingleton mAllFolders;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);
        mCardId = (UUID)getArguments().getSerializable("cardId");
        mDeckId = (UUID)getArguments().getSerializable("deckId");
        mFolderId = (UUID)getArguments().getSerializable("folderId");
        mAllFolders = FoldersSingleton.get();
        mCard = mAllFolders.getFolder(mFolderId).getDeck(mDeckId).getCard(mCardId);
        mCards = mAllFolders.getFolder(mFolderId).getDeck(mDeckId).getCards();
    }

    public View onCreateView(LayoutInflater li, ViewGroup vg,
                             Bundle sis)
    {
        View v = li.inflate(R.layout.fragment_card, vg, false);


        mCardSide = (TextView)v.findViewById(R.id.frontBackTitle);
        mCardContent = (TextView)v.findViewById(R.id.cardContent);

        if(mCard.isOnFront()) {
            mCardContent.setText(mCard.getFrontSideText());
            mCardSide.setText("Front");
        }
        else {
            mCardContent.setText(mCard.getBackSideText());
            mCardSide.setText("Back");
        }

        mCardSide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCard.isOnFront()) {
                    mCardContent.setBackgroundResource(R.drawable.back_side_background);
                    mCardContent.setText(mCard.getBackSideText());
                    mCardSide.setText("Back");
                    mCard.setOnFront(false);
                } else {
                    mCardContent.setBackgroundResource(R.drawable.card_border);
                    mCardContent.setText(mCard.getFrontSideText());
                    mCardSide.setText("Front");
                    mCard.setOnFront(true);
                }
            }
        });
        mCardContent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mCard.isOnFront()) {
                    mCardContent.setBackgroundResource(R.drawable.back_side_background);
                    mCardContent.setText(mCard.getBackSideText());
                    mCardSide.setText("Back");
                    mCard.setOnFront(false);
                }
                else {
                    mCardContent.setBackgroundResource(R.drawable.card_border);
                    mCardContent.setText(mCard.getFrontSideText());
                    mCardSide.setText("Front");
                    mCard.setOnFront(true);
                }
            }
        });

        mEditCard = (EditText)v.findViewById(R.id.editCardContent);
        mEditCardButton = (Button)v.findViewById(R.id.editContent);
        mEditCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText;
                if(editModeEnabled) {
                    mEditCardButton.setText(R.string.save_card);
                    mCardContent.setVisibility(View.INVISIBLE);
                    mEditCard.setVisibility(View.VISIBLE);
                    mEditCard.requestFocus();
                    if (mCard.isOnFront())
                        mEditCard.setText(mCard.getFrontSideText());
                    else
                        mEditCard.setText(mCard.getBackSideText());
                    editModeEnabled = false;
                }
                else {
                    mEditCardButton.setText(R.string.edit_card);
                    newText = mEditCard.getText().toString();
                    mEditCard.setVisibility(View.INVISIBLE);
                    mCardContent.setVisibility(View.VISIBLE);
                    if (mCard.isOnFront()) {
                        mCard.setFrontSideText(newText);
                        mAllFolders.updateCard(mCard, mDeckId, mFolderId);
                        mCardContent.setText(mCard.getFrontSideText());
                    }
                    else {
                        mCard.setBackSideText(newText);
                        mAllFolders.updateCard(mCard, mDeckId, mFolderId);
                        mCardContent.setText(mCard.getBackSideText());
                    }
                    editModeEnabled = true;
                }
            }
        });

        return v;

    }
}
