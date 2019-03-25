package kz.baqshamninonimi.model;

public class Product {
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
