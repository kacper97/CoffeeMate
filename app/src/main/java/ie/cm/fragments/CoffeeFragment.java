package ie.cm.fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import ie.cm.activities.Base;
import ie.cm.adapters.CoffeeListAdapter;
import ie.cm.models.Coffee;

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
    public void onClick(View view){
        if(view.getTag()instanceof Coffee)
        {
            onCoffeeDelete((Coffee) view.getTag());
        }
    }

    public void onCoffeeDelete(final Coffee coffee)
    {
        String stringName = coffee.coffeeName;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Are you sure you want to Delete the \'Coffee\' " + stringName + "?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                Base.coffeeList.remove(coffee); // remove from our list
                listAdapter.coffeeList.remove(coffee); // update adapters data
                listAdapter.notifyDataSetChanged(); // refresh adapter
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
