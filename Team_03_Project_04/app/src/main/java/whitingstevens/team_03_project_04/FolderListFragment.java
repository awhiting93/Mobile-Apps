package whitingstevens.team_03_project_04;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 121667 on 11/28/2015.
 */
public class FolderListFragment extends Fragment{
    RecyclerView mView;
    FolderAdapter mAdapter;
    FoldersSingleton mFoldersSingleton;
    ArrayList<Folder> mFolders;
    String newItemTitle;

    @Override
    public void onCreate(Bundle sis){
        super.onCreate(sis);
    }

    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle Sis)
    {
        View v = li.inflate(R.layout.folder_list_fragment, vg, false);
        mView = (RecyclerView)v.findViewById(R.id.folderList);
        mView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mFoldersSingleton = FoldersSingleton.get(getActivity().getApplicationContext());
        mFolders = mFoldersSingleton.getFolders();
        mAdapter = new FolderAdapter(mFolders);
        mView.setAdapter(mAdapter);
        return v;
    }

    public class FolderHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        Folder mFolder;
        private TextView mFolderName;
        private Button mDeleteButton;

        @Override
        public void onClick(View v){
            Log.d("test", "onClick");
            Intent i = new Intent(getActivity(), SelectDeckActivity.class);
            i.putExtra("folderName", mFolder.getFolderName());
            i.putExtra("folderId", mFolder.getId());
            Log.d("test", "End onClick");
            startActivity(i);
        }

        public FolderHolder(View item){
            super(item);
            item.setOnClickListener(this);
            mFolderName = (TextView)item.findViewById(R.id.folderTitle);
            mDeleteButton = (Button)item.findViewById(R.id.deleteButton);

        }

        public void bindFolder(Folder f) {
            mFolder = f;
            mFolderName.setText(f.getFolderName());

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFoldersSingleton.deleteFolder(mFolder);
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    public class FolderAdapter extends RecyclerView.Adapter<FolderHolder>{
        ArrayList<Folder> mFolders;

        public FolderAdapter(ArrayList<Folder> folders) {mFolders = folders;}

        @Override
        public FolderHolder onCreateViewHolder(ViewGroup vg, int type){
            LayoutInflater li = LayoutInflater.from(getActivity());
            View v = li.inflate(R.layout.list_item_folder, vg, false);
            return new FolderHolder(v);
        }

        @Override
        public void onBindViewHolder(FolderHolder ch, int position){
            Folder folder = mFolders.get(position);
            ch.bindFolder(folder);
        }

        @Override
        public int getItemCount(){return mFolders.size();}
    }

    @Override
    public void onResume()
    {
        FoldersSingleton lab = FoldersSingleton.get(getActivity().getApplicationContext());
        ArrayList<Folder> mFolder = lab.getFolders();
        super.onResume();
        if(mAdapter == null)
        {
            mAdapter = new FolderAdapter(mFolder);
        }
        else
        {
            mAdapter.notifyDataSetChanged();
        }

    }
}
