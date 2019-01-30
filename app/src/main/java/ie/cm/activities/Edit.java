package ie.cm.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
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
        activityInfo = getIntent().getExtras();
        aCoffee = getCoffeeObject(activityInfo.getString("coffeeId"));

        Log.v("coffeemate", "EDIT : " + aCoffee);

        ((TextView)findViewById(R.id.editTitleTV)).setText(aCoffee.coffeeName);

        ((EditText)findViewById(R.id.editNameET)).setText(aCoffee.coffeeName);
        ((TextView)findViewById(R.id.editShopET)).setText(aCoffee.shop);
        ((EditText)findViewById(R.id.editPriceET)).setText(""+aCoffee.price);
        ((RatingBar) findViewById(R.id.editRatingBar)).setRating((float)aCoffee.rating);

        editFavourite = findViewById(R.id.editFavourite);

        if (aCoffee.favourite == true) {
            editFavourite.setImageResource(R.drawable.favourites_72_on);
            isFavourite = true;
        } else {
            editFavourite.setImageResource(R.drawable.favourites_72);
            isFavourite = false;
        }
    }

    private Coffee getCoffeeObject(String id) {

        for (Coffee c : coffeeList)
            if (c.coffeeId.equalsIgnoreCase(id))
                return c;

        return null;
    }

//    private int getCoffeeIndex(Coffee obj) {
//
//        for (Coffee c : coffeeList)
//            if (c.coffeeId == obj.coffeeId)
//                return coffeeList.indexOf(c);
//
//        return -1;
//    }

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

            startActivity(new Intent(this,Home.class));

        } else
            Toast.makeText(this, "You must Enter Something for Name and Shop",Toast.LENGTH_SHORT).show();
    }

    public void toggle(View view) {

        if (isFavourite) {
            aCoffee.favourite = false;
            Toast.makeText(this,"Removed From Favourites",Toast.LENGTH_SHORT).show();
            isFavourite = false;
            editFavourite.setImageResource(R.drawable.favourites_72);
        } else {
            aCoffee.favourite = true;
            Toast.makeText(this,"Added to Favourites !!",Toast.LENGTH_SHORT).show();
            isFavourite = true;
            editFavourite.setImageResource(R.drawable.favourites_72_on);
        }
    }
}
