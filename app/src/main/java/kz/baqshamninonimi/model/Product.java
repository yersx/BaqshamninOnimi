package kz.baqshamninonimi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int id;
    private String title;
    private String shortdesc;
    private double rating;
    private double price;
    private String image;
    private String farmer;

    public Product(int id, String title, String shortdesc, double rating, double price, String image,String farmer) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
        this.farmer = farmer;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        title = in.readString();
        shortdesc = in.readString();
        rating = in.readDouble();
        price = in.readDouble();
        image = in.readString();
        farmer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(shortdesc);
        dest.writeDouble(rating);
        dest.writeDouble(price);
        dest.writeString(image);
        dest.writeString(farmer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getFarmer() {
        return farmer;
    }
}
