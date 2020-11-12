import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine {
    private HashMap<Integer, Item> itemMapping;

    public HashMap<Integer, Item> getItemMapping() {
        return itemMapping;
    }

    public VendingMachine(){
        this.itemMapping = new HashMap<>();
    }

    public void addItem(Item item){
        int key = item.hashCode();
        if (itemMapping.containsKey(key)){
            System.out.println("Duplicate key. Item not added.");
        }else {
            itemMapping.put(key, item);
        }
    }

    public void addItems(ArrayList<Item> items){
        for (Item i: items) {
            addItem(i);
        }
    }

    public Item getItem(int key){
        return itemMapping.get(key);
    }

    public int checkStock(int key){
        return itemMapping.get(key).getStock();
    }

}
