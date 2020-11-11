import java.util.HashMap;
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

    public String getName(){return name; }

    public void setName(String name){this.name = name; }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) { this.category = category; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) { this.price = price; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return (name.equals(item.name) && category == item.category && price == item.price && stock == item.stock);
    }

    @Override
    public int hashCode() {
        // fixed prime used to calculate the result for each attribute
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((category == null) ? 0 : category.hashCode());
        result = (int) (prime * result + price);
        result = prime * result + stock;

        return result;
    }
}
