public class Main {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        Item i1 = new Item("Doritos Chilli Heatwave",Category.CRISP, 2.95, 2);
        // test item that's equal to i1 used for testing hashCode and equals methods
        Item i2 = new Item("Doritos Chilli Heatwave",Category.CRISP, 2.95, 2);
        // test item that has the same hash location as i1 (21)
        Item i3 = new Item("Hersheysss", Category.SWEETS, 2.11, 21);

        Item i4 = new Item("Tangfastics", Category.SWEETS, 2.95, 2);

        vm.addItem(i1);
        vm.addItem(i2);
        vm.addItem(i3);
        vm.addItem(i4);

        for (int i: vm.getItemMapping().keySet()){
            System.out.println(i + ": " + vm.getItemMapping().get(i));
        }
    }
}
