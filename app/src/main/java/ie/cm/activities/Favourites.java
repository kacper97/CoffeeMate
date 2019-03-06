package ie.cm.activities;

import android.os.Bundle;
import android.widget.TextView;
import ie.cm.R;
import ie.cm.fragments.CoffeeFragment;

public class Favourites extends Base {

    private TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites);

        emptyList = findViewById(R.id.emptyList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(app.coffeeList.isEmpty())
            emptyList.setText(getString(R.string.emptyMessageLbl));
        else
            emptyList.setText("");

        coffeeFragment = CoffeeFragment.newInstance(); //get a new Fragment instance
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, coffeeFragment)
                .commit(); // add it to the current activity
    }
}
