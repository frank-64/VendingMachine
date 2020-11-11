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
        final int prime = 31;
        // getting the hashcode for the item
        int key = item.hashCode();
        System.out.println(key);
        // getting the key location which is the modulo of the absolute value of the hashcode with the prime divisor 31
        // note: this only allows me to have a maximum of 31 items stored in the vending machine
        key = Math.floorMod(Math.abs(key), prime);
        System.out.println(key);
        itemMapping.put(key, item);
    }
}
