package ie.cm.activities;
import android.os.Bundle;
import ie.cm.R;
import ie.cm.fragments.CoffeeFragment;

public class Search extends Base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
    }

    @Override
    protected void onResume() {
        super.onResume();
        coffeeFragment = CoffeeFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, coffeeFragment)
                .commit(); // add it to the current activity
    }

}
