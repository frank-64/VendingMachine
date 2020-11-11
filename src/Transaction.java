import java.util.ArrayList;
import java.util.Map;

public class Transaction extends VendingMachine {
    private ArrayList<Item> items;
    private ArrayList<Integer> quantity;

    // transaction of more than one item
    public Transaction(ArrayList<Item> items, ArrayList<Integer> quantity){
        this.items = items;
        this.quantity = quantity;
    }

    // transaction of one item
    public Transaction(Item item, int quantity){
        this.items = new ArrayList<>();
        this.quantity = new ArrayList<>();
        this.items.add(item);
        this.quantity.add(quantity);
    }

    public double calculateCost(){
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            Item item_temp = items.get(i);
            total += item_temp.getPrice() * quantity.get(i);
        }
        return total;
    }
}
