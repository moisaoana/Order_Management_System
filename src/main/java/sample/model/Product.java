package sample.model;

public class Product {
    private int ID;
    private String name;
    private double price;
    private String brand;
    private String type;
    private int quantity;
    public Product(){

    }
    public Product(int ID,String name,double price,String brand,String type,int quantity){
        this.ID=ID;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.brand=brand;
        this.type=type;
    }
    public Product(String name,double price,String brand,String type,int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.brand=brand;
        this.type=type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
