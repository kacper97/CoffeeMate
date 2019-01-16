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
import ie.cm.models.Coffee;

public class Home extends Base {

    TextView emptyList;
    ListView coffeeListView;
    ArrayAdapter<Coffee> coffeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emptyList = findViewById(R.id.emptyList);
        coffeeListView = findViewById(R.id.recentlyAddedList);
        coffeeListView.setEmptyView(emptyList);
        coffeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,coffeeList);
        coffeeListView.setAdapter(coffeeAdapter);

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
    }

    public void add(View v) {
        startActivity(new Intent(this, Add.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("coffeemate","Home : " + coffeeList);

        if(!coffeeList.isEmpty())
            coffeeAdapter.notifyDataSetChanged();
    }
}