import com.sun.jdi.InvalidTypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.*;

public class VendingGUI{


    private JPanel mainPanel;
    private JLabel title;
    private JButton item_btn_1;
    private JButton item_btn_2;
    private JButton item_btn_3;
    private JButton item_btn_4;
    private JButton item_btn_5;
    private JButton item_btn_6;
    private JLabel label1;
    private JLabel label3;
    private JLabel label2;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JTextArea itemChosenList;
    private JTextArea stockList;
    private JLabel subTotLbl;
    private JButton resetBtn;
    private JButton checkoutBtn;
    private JTextField paidAmt;
    private JLabel subTotal;
    private JLabel errorLbl;
    private JLabel itemsChosenLbl;
    private JLabel changeLbl;
    private JLabel changeValue;
    private JLabel paidLbl;
    private JLabel stockLbl;
    private ArrayList<Item> items;
    private ArrayList<JLabel> labels;
    private ArrayList<JButton> buttons;
    private HashMap<JButton, Integer> btnMap;
    private ArrayList<ItemQuantity> itemQuantities;
    private Transaction currentTrans;

    public VendingGUI() {
        VendingMachine vm = new VendingMachine();
        items = new ArrayList<>();
        labels = new ArrayList<>();
        buttons = new ArrayList<>();
        btnMap = new HashMap<>();
        itemQuantities = new ArrayList<>();
        init();
        vm.addItems(items);
        updateStockList();

        item_btn_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hashKey = btnMap.get(item_btn_1);
                Item i = vm.getItem(hashKey);
                int stock = i.getStock();
                if (stock == 0){
                    item_btn_1.setText("No stock!");
                }else {
                    itemChosen(i);
                    updateStockList();
                    updateChosenList();
                    updateSubtotal();

                }
            }
        });
        item_btn_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hashKey = btnMap.get(item_btn_2);
                Item i = vm.getItem(hashKey);
                int stock = i.getStock();
                if (stock == 0){
                    item_btn_2.setText("No stock!");
                }else {
                    itemChosen(i);
                    updateStockList();
                    updateChosenList();
                    updateSubtotal();
                }
            }
        });
        item_btn_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hashKey = btnMap.get(item_btn_3);
                Item i = vm.getItem(hashKey);
                int stock = i.getStock();
                if (stock == 0){
                    item_btn_3.setText("No stock!");
                }else {
                    itemChosen(i);
                    updateStockList();
                    updateChosenList();
                    updateSubtotal();
                }
            }
        });
        item_btn_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hashKey = btnMap.get(item_btn_4);
                Item i = vm.getItem(hashKey);
                int stock = i.getStock();
                if (stock == 0){
                    item_btn_4.setText("No stock!");
                }else {
                    itemChosen(i);
                    updateStockList();
                    updateChosenList();
                    updateSubtotal();
                }
            }
        });
        item_btn_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hashKey = btnMap.get(item_btn_5);
                Item i = vm.getItem(hashKey);
                int stock = i.getStock();
                if (stock == 0){
                    item_btn_5.setText("No stock!");
                }else {
                    itemChosen(i);
                    updateStockList();
                    updateChosenList();
                    updateSubtotal();
                }
            }
        });
        item_btn_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hashKey = btnMap.get(item_btn_6);
                Item i = vm.getItem(hashKey);
                int stock = i.getStock();
                if (stock == 0){
                    item_btn_6.setText("No stock!");
                }else {
                    itemChosen(i);
                    updateStockList();
                    updateChosenList();
                    updateSubtotal();
                }
            }
        });
        paidAmt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transaction trans = new Transaction();
                double total = trans.calculateCost(itemQuantities);
                String user_input = paidAmt.getText();
                try{
                    if(Double.parseDouble(user_input)<total){
                        errorLbl.setText("You must pay more!");
                    }else {
                        errorLbl.setText("");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void resetVm(){

    }

    public void updateSubtotal(){
        Transaction temp = new Transaction();
        DecimalFormat df = new DecimalFormat("#0.00");
        double subtotal = temp.calculateCost(itemQuantities);
        String stringValue = df.format(subtotal);
        subTotal.setText("£"+stringValue);
    }

    public void updateStockList(){
        StringBuilder sb = new StringBuilder();
        for (Item i:this.items) {
            sb.append(i.getName()).append(": ").append(i.getStock()).append("\n");
        }
        stockList.setText(sb.toString());
    }

    public void updateChosenList(){
        StringBuilder sb = new StringBuilder();
        for (ItemQuantity iq:this.itemQuantities) {
            sb.append(iq.getItem().getName()).append(" x").append(iq.getQuantity()).append("\n");
        }
        itemChosenList.setText(sb.toString());
    }

    public void itemChosen(Item i){
        boolean duplicate = false;
        int index = 0;
        for (ItemQuantity iq: this.itemQuantities) {
            if (iq.getItem().equals(i)) {
                duplicate = true;
                break;
            }
            index++;
        }
        if(duplicate){
            ItemQuantity existing_iq = this.itemQuantities.get(index);
            existing_iq.setQuantity(existing_iq.getQuantity()+1);
        }else {
            ItemQuantity itemQuantity = new ItemQuantity(i, 1);
            this.itemQuantities.add(itemQuantity);
        }

        // decrementing the stock of the item
        i.setStock(i.getStock()-1);
    }

    public void init(){
        items = new ArrayList<>();
        Item i1 = new Item("Tangfastics", Category.SWEETS, 1.50, 10);
        Item i2 = new Item("Quavers", Category.CRISP, 0.75, 33);
        Item i3 = new Item("Diet Coke", Category.DRINK, 0.80, 25);
        Item i4 = new Item("Maltesers", Category.SWEETS, 1.50, 0);
        Item i5 = new Item("McColls Salt & Vinegar", Category.CRISP, 0.90, 20);
        Item i6 = new Item("RedBull", Category.DRINK, 1.50, 12);
        // adding items to it's ArrayList attribute
        Collections.addAll(this.items, i1,i2,i3,i4,i5,i6);

        // adding the labels and the buttons to their own ArrayLists
        Collections.addAll(this.labels, label1, label2, label3, label4, label5, label6);
        Collections.addAll(this.buttons, item_btn_1, item_btn_2, item_btn_3, item_btn_4, item_btn_5, item_btn_6);
        int count = 0;

        // sets the labels on the GUI equal to the names and prices of the corresponding items
        for (JLabel label:this.labels) {
            Item temp_item = this.items.get(count);
            DecimalFormat df = new DecimalFormat("#0.00");
            double subtotal = temp_item.getPrice();
            String stringValue = df.format(subtotal);
            label.setText(count+1+". "+temp_item.getName()+": £"+stringValue);
            count++;
        }

        // assigning item hashcode to each button
        count = 0;
        for (JButton button: this.buttons) {
            Item temp_item = this.items.get(count);
            this.btnMap.put(button, temp_item.hashCode());
            button.setText((count+1)+".");
            count++;
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Vending Machine");
        frame.setContentPane(new VendingGUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500,600));
        frame.setMaximumSize(new Dimension(600,700));
        frame.pack();
        frame.setVisible(true);
    }

}
