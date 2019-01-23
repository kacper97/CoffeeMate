package ie.cm.fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import ie.cm.activities.Base;
import ie.cm.adapters.CoffeeListAdapter;

public class CoffeeFragment  extends ListFragment implements View.OnClickListener
{
    public Base activity;
    public static CoffeeListAdapter listAdapter;
    public ListView listView;

    public CoffeeFragment() {
        // Required empty public constructor
    }

    public static CoffeeFragment newInstance() {
        CoffeeFragment fragment = new CoffeeFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.activity = (Base) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        listAdapter = new CoffeeListAdapter(activity,this,Base.coffeeList);
        setListAdapter(listAdapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onClick(View view)
    {
    }
}
