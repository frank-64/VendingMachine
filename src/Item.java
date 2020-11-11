import java.util.Objects;

public class Item {
    private String name;
    private Category category;
    private double price;
    private int stock;

    public Item(String name, Category category, double price, int stock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) { this.category = category; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    public static void main(String[] args) {
        Item i1 = new Item("Doritos Chilli Heatwave",Category.SWEETS, 2.95, 2);
        System.out.println(i1.hashCode());
    }
}
