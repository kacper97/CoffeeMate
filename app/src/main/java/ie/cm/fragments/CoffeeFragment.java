package ie.cm.fragments;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import ie.cm.R;
import ie.cm.activities.Base;
import ie.cm.activities.Edit;
import ie.cm.adapters.CoffeeListAdapter;
import ie.cm.models.Coffee;

public class CoffeeFragment  extends ListFragment implements View.OnClickListener, AbsListView.MultiChoiceModeListener
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);

        listView = v.findViewById(android.R.id.list);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(this);

        return v;
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

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Bundle activityInfo = new Bundle(); // Creates a new Bundle object
        activityInfo.putString("coffeeId", (String) v.getTag());

        Intent goEdit = new Intent(getActivity(), Edit.class); // Creates a new Intent
        /* Add the bundle to the intent here */
        getActivity().startActivity(goEdit); // Launch the Intent
        goEdit.putExtras(activityInfo);
        getActivity().startActivity(goEdit);
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        MenuInflater inflater = actionMode.getMenuInflater();
        inflater.inflate(R.menu.delete_list_context,menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        {
            switch(menuItem.getItemId())
            {
                case R.id.menu_item_delete_coffee:
                    deleteCoffees(actionMode);
                    return true;
                default:
                    return false;
            }
        }
    }

    private void deleteCoffees(ActionMode actionMode) {
        for (int i = listAdapter.getCount() - 1; i >= 0; i--)
        {
            if (listView.isItemChecked(i))
            {
                Base.coffeeList.remove(listAdapter.getItem(i));
            }
        }
        actionMode.finish();
        listAdapter.notifyDataSetChanged();
    }
    

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {

    }
}
