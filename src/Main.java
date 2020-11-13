import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        VendingMachine vm = new VendingMachine();
//        Item i1 = new Item("Doritos Chilli Heatwave",Category.CRISP, 5, 2);
//        // test item that's equal to i1 used for testing hashCode and equals methods
//        Item i2 = new Item("Doritos Chilli Heatwave",Category.CRISP, 5, 2);
//        // test item that has the same hash location as i1 (21)
//        Item i3 = new Item("Hershey's", Category.SWEETS, 2.50, 21);
//
//        Item i4 = new Item("Tangfastics", Category.SWEETS, 2.05, 2);
//
//        vm.addItem(i1);
//        vm.addItem(i3);
//        vm.addItem(i4);
//
//        for (int i: vm.getItemMapping().keySet()){
//            System.out.println(i + ": " + vm.getItemMapping().get(i));
//        }

        ChangeCalculation c = new ChangeCalculation();
        int V = 123;
        c.recursiveChange(0, V);
        System.out.println(c.getChange());

//        int n = 50;
//        double n_double = ((double) n)/100;
//        System.out.println(n_double);

    }
}
