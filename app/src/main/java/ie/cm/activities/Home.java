package ie.cm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ie.cm.R;
import ie.cm.fragments.CoffeeFragment;
import ie.cm.models.Coffee;

public class Home extends Base {

    TextView emptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emptyList = findViewById(R.id.emptyList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Information", Snackbar.LENGTH_LONG)
                        .setAction("More Info...", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).show();
            }
        });
        if(coffeeList.isEmpty()) setupCoffees();
    }

    public void add(View v) {
        startActivity(new Intent(this, Add.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(coffeeList.isEmpty())
            emptyList.setText(getString(R.string.emptyMessageLbl));
        else
            emptyList.setText("");

        coffeeFragment = CoffeeFragment.newInstance(); //get a new Fragment instance
        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, coffeeFragment)
                .commit(); // add it to the current activity

    }

    public void setupCoffees(){
        coffeeList.add(new Coffee("Standard Black", "Some Shop",2.5,1.99,false));
        coffeeList.add(new Coffee("Regular Joe", "Joe's Place",3.5,2.99,true));
        coffeeList.add(new Coffee("Espresso", "Ardkeen Stores",4.5,1.49,true));
    }

}

