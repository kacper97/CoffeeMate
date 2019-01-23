package ie.cm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ie.cm.R;
import ie.cm.models.Coffee;

public class CoffeeItem {
    View view;

    public CoffeeItem(Context context, ViewGroup parent,
                      View.OnClickListener deleteListener, Coffee coffee)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.coffeerow, parent, false);
        view.setTag(coffee.coffeeId);

        updateControls(coffee);

        ImageView imgDelete = (ImageView) view.findViewById(R.id.rowDeleteImg);
        imgDelete.setTag(coffee);
        imgDelete.setOnClickListener(deleteListener);
    }

    private void updateControls(Coffee coffee) {
        ((TextView) view.findViewById(R.id.rowCoffeeName)).setText(coffee.coffeeName);

        // Do the same for shop, rating, price & the favourite image here
        // and set the favourite on/off depending on the coffees favourite value

    }
}
