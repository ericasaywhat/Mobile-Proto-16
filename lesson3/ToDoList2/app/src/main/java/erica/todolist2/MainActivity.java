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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Fragment listFrag = new ListFragment();
        replaceFragment(listFrag); // default view is the list fragment

        Button listFragmentButton = (Button) findViewById(R.id.button);
        listFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(listFrag); // Go back to the list fragment
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * When "Settings" is called uses replaceFragment method defined below to replace the current
     * fragment with settings fragment
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // There is an option called "Settings" in the menu
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Fragment settingsFrag = new SettingsFragment();
            replaceFragment(settingsFrag);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * method replaces the current fragment with the fragment that is given as an input
     */
    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null).commit();
    }
}
