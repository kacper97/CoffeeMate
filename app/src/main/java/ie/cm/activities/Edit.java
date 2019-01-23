package ie.cm.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import ie.cm.R;
import ie.cm.models.Coffee;

public class Edit extends Base {
    public Context context;
    public boolean isFavourite;
    public Coffee aCoffee;
    public ImageView editFavourite;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        context = this;
    }

    public void saveCoffee(View v) {

        String coffeeName = ((EditText) findViewById(R.id.editNameET)).getText().toString();
        String coffeeShop = ((EditText) findViewById(R.id.editShopET)).getText().toString();
        String coffeePriceStr = ((EditText) findViewById(R.id.editPriceET)).getText().toString();
        double ratingValue =((RatingBar) findViewById(R.id.editRatingBar)).getRating();
        double coffeePrice;

        try {
            coffeePrice = Double.parseDouble(coffeePriceStr);
        } catch (NumberFormatException e) {
            coffeePrice = 0.0;
        }

        if ((coffeeName.length() > 0) && (coffeeShop.length() > 0) && (coffeePriceStr.length() > 0)) {
            aCoffee.coffeeName = coffeeName;
            aCoffee.shop = coffeeShop;
            aCoffee.price = coffeePrice;
            aCoffee.rating = ratingValue;

            // Update coffee & return home

        } else
            Toast.makeText(this, "You must Enter Something for Name and Shop",Toast.LENGTH_SHORT).show();
    }
}
