package erica.todolist2;

import android.content.Context;
import android.content.SharedPreferences;
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

    private int myColor;

    public int getMyColor() {
        return myColor;
    }

    public void setMyColor(int myColor) {
        this.myColor = myColor;
    }

    @BindView(R.id.button) Button button;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

        int color = sharedPref.getInt(getString(R.string.saved_background), R.string.saved_background_default);
        setMyColor(color);


        setContentView(R.layout.activity_main);                                                     //gets the layout for the view from activity main
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);                                                               //makes the toolbar

        findViewById(R.id.act_main).setBackgroundColor(color);

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
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_background), myColor);
        editor.apply();
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

