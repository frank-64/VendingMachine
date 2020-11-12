import java.util.ArrayList;
import java.util.Map;

public class Transaction extends VendingMachine {
    private ArrayList<ItemQuantity> itemQuantities;

    // transaction of more than one item
    public Transaction(ArrayList<ItemQuantity> itemQuantities) {
        this.itemQuantities = itemQuantities;
    }

    public double calculateCost(){
        double total = 0;
        for (int i = 0; i < itemQuantities.size(); i++) {
            Item item_temp = itemQuantities.get(i).getItem();
            total += item_temp.getPrice() * itemQuantities.get(i).getQuantity();
        }
        return total;
    }
}
