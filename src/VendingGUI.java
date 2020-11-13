import com.sun.jdi.InvalidTypeException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.*;

public class VendingGUI extends JFrame implements ActionListener{


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
    private JLabel paidLbl;
    private JLabel stockLbl;
    private JLabel changeLbl;
    private JLabel changeValue;
    private JTextArea changeBreakdown;
    private ArrayList<Item> items;
    private ArrayList<JLabel> labels;
    private ArrayList<JButton> buttons;
    private HashMap<JButton, Integer> btnMap;
    private ArrayList<ItemQuantity> itemQuantities;
    private VendingMachine vm;
    private double total;
    private double paid;
    private double change;

    public VendingGUI() {
        vm = new VendingMachine();
        items = new ArrayList<>();
        initItems();
        labels = new ArrayList<>();
        buttons = new ArrayList<>();
        btnMap = new HashMap<>();
        itemQuantities = new ArrayList<>();
        initLblBtn();
        vm.addItems(items);
        updateStockList();

        // buttons ActionListener
        this.item_btn_1.addActionListener(this);
        this.item_btn_2.addActionListener(this);
        this.item_btn_3.addActionListener(this);
        this.item_btn_4.addActionListener(this);
        this.item_btn_5.addActionListener(this);
        this.item_btn_6.addActionListener(this);

        checkoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    checkPaid();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetVm();
            }
        });
    }

    public void resetVm(){
        updateStockList();
        itemQuantities = new ArrayList<>();
        total=0;
        paid=0;
        change=0;
        itemChosenList.setText("");
        changeBreakdown.setText("");
        changeValue.setText("");
        subTotal.setText("");
        paidAmt.setText("");
        errorLbl.setText("");
    }

    public void startTransaction() throws InterruptedException {
        Transaction t = new Transaction();
        int total_conversion = ((int) (change*100));
        t.recursiveChange(0, total_conversion);
        String change_result = t.getChange();
        if (change_result==null){
            errorLbl.setText("Change not possible.");
            resetVm();
        }else {
            changeBreakdown.setText(change_result);
            vm.updateStock(itemQuantities);
            updateStockList();
            errorLbl.setText("Transaction Complete!");
        }
    }

    public void checkPaid() throws InterruptedException {
        String user_input = paidAmt.getText();
        paid = Double.parseDouble(user_input);
        change = (((double) Math.round((paid-total)*100))/100);
        DecimalFormat df = new DecimalFormat("#0.00");
        String change_string = "£"+df.format(change);
            if(paid<total){
                errorLbl.setText("You must pay more!");
            }else {
                errorLbl.setText("");
                changeValue.setText(change_string);
                startTransaction();
            }
        }

    public void updateSubtotal(){
        Transaction temp = new Transaction();
        DecimalFormat df = new DecimalFormat("#0.00");
        double subtotal = temp.calculateCost(itemQuantities);
        total = subtotal;
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
    }

    public void initItems(){
        items = new ArrayList<>();
        Item i1 = new Item("Tangfastics", Category.SWEETS, 1.50, 10);
        Item i2 = new Item("Quavers", Category.CRISP, 0.75, 33);
        Item i3 = new Item("Diet Coke", Category.DRINK, 0.80, 25);
        Item i4 = new Item("Maltesers", Category.SWEETS, 1.50, 0);
        Item i5 = new Item("McColls Salt & Vinegar", Category.CRISP, 0.90, 20);
        Item i6 = new Item("RedBull", Category.DRINK, 1.50, 12);
        // adding items to it's ArrayList attribute
        Collections.addAll(this.items, i1,i2,i3,i4,i5,i6);
    }

    public void initLblBtn(){
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

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        int hashKey = btnMap.get(button);
        Item i = vm.getItem(hashKey);
        int stock = i.getStock();
        if (stock == 0){
            button.setText("No stock!");
        }else {
            itemChosen(i);
            updateChosenList();
            updateSubtotal();
        }
        System.out.println("WORKING!");
    }
}
