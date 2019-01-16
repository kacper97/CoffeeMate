package ie.cm.models;

import java.io.Serializable;

public class Coffee implements Serializable {
    public int coffeeId;
    public String coffeeName;
    public String shop;
    public double rating;
    public double price;
    public boolean favourite;


    public Coffee() {}

    public Coffee(String name, String shop, double rating, double price, boolean fav)
    {
        this.coffeeName = name;
        this.shop = shop;
        this.rating = rating;
        this.price = price;
        this.favourite = fav;
    }

    @Override
    public String toString() {
        return coffeeName + ", " + shop + ", " + rating
                + ", " + price + ", fav =" + favourite;
    }
}
