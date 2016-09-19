package erica.todolist2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button) Button button;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                                                     //gets the layout for the view from activity main
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);                                                               //makes the toolbar

        final Fragment listFrag = new MyListFragment();
        replaceFragment(listFrag);                                                                  //calls the replaceFragment method defined below for the current view to be the list fragment


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(listFrag);                                                          //when the button is clicked view goes back to list fragment
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {                                           //there is an option called "Settings" in the menu
        /**When "Settings" is called uses replaceFragment method
         *defined below to replace the current fragment with settings fragment */
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Fragment settingsFrag = new SettingsFragment();
            replaceFragment(settingsFrag);
        }

        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment) {
        /**method replaces the current fragment with the fragment
         that is given as an input */
        FragmentManager manager;                                                                    //initializes manager as FragmentManager
        FragmentTransaction transaction;                                                            //initializes transaction as FragmentTransaction

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null).commit();
    }
}

