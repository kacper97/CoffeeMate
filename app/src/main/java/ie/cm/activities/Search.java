package ie.cm.activities;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import ie.cm.R;
import ie.cm.fragments.SearchFragment;

public class Search extends Base  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }

    @Override
    protected void onResume() {
        super.onResume();

        coffeeFragment = SearchFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, coffeeFragment)
                .commit(); // add it to the current activity
    }
}
