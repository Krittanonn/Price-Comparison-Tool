package src;
public class Product {
    private String name;
    private double size;
    private double price;
    private double pricePerUnit;

    // Constructor
    public Product(String name, double size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.pricePerUnit = price / size;  // คำนวณราคาต่อหน่วย
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public String toString() {
        return String.format("%s - Size: %.2f | Price: %.2f USD | Price per unit: %.2f USD",
                name, size, price, pricePerUnit);
    }
}
