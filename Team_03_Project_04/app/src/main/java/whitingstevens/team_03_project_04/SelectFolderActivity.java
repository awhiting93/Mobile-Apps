package whitingstevens.team_03_project_04;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SelectFolderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_folder);
        Log.d("test", "Started on create selectFolderActivity");
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.folderFragmentHolder);

        if(fragment == null){
            fragment = new FolderListFragment();
            fm.beginTransaction().add(R.id.folderFragmentHolder, fragment).commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_folder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final EditText newItemTitle;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            View v = LayoutInflater.from(this).inflate(R.layout.new_item_dialog_fragment, null);
            newItemTitle = (EditText)v.findViewById(R.id.editTitle);
            AlertDialog getNewItemTitle = new AlertDialog.Builder(this)
                    .setView(v)
                    .setTitle(R.string.new_folder_title)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FoldersSingleton.get(getApplicationContext())
                                    .addFolder(new Folder(newItemTitle.getText().toString()));
                            Fragment fragment = new FolderListFragment();
                            FragmentManager fm = getSupportFragmentManager();
                            fm.beginTransaction().replace(R.id.folderFragmentHolder, fragment).commit();
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
