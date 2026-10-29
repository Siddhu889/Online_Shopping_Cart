
package model;

public class Product {

    private int id;
    private String name;
    private double price;
    private int quantity;
    private String image;
    private int categoryid;
    private String description;
    private String details;
    private String availability;
    private String features;

    public Product() {

    }

    public Product(int id, String name, double price, int quantity,
            String image, int categoryid, String description,
            String details, String availability, String features) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.categoryid = categoryid;
        this.description = description;
        this.details = details;
        this.availability = availability;
        this.features = features;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategory_id() {
        return categoryid;
    }

    public void setCategory_id(int category_id) {
        this.categoryid = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }
}
