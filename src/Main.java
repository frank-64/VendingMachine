import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        Item i1 = new Item("Doritos Chilli Heatwave",Category.CRISP, 5, 2);
        // test item that's equal to i1 used for testing hashCode and equals methods
        Item i2 = new Item("Doritos Chilli Heatwave",Category.CRISP, 5, 2);
        // test item that has the same hash location as i1 (21)
        Item i3 = new Item("Hershey's", Category.SWEETS, 2.50, 21);

        Item i4 = new Item("Tangfastics", Category.SWEETS, 2.05, 2);

        vm.addItem(i1);
        vm.addItem(i3);
        vm.addItem(i4);

        for (int i: vm.getItemMapping().keySet()){
            System.out.println(i + ": " + vm.getItemMapping().get(i));
        }

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(i1);
        items.add(i4);
        ArrayList<Integer> quantity = new ArrayList<>();
        quantity.add(2);
        quantity.add(3);
        double n = 19.95;
        n *= 100;
        int n_int = (int) n;

        System.out.println(Math.floorMod(123, 123));
        System.out.println(Math.floorDiv(123,123));
    }
}
