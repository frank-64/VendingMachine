import java.util.HashMap;

public class VendingMachine {
    private HashMap<Object, Item> stock;

    public VendingMachine(HashMap stock){
        this.stock = stock;
    }
}
