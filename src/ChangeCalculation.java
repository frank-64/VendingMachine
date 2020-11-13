import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ChangeCalculation {
    private final int[] coins;
    private ArrayList<CoinQuantity> coinQuantities;

    public ChangeCalculation() {
        // 1=one penny, 100=one pound...
        coins = new int[]{2000,1000,100,10,5,1};
        coinQuantities = new ArrayList<>();
    }


    public int recursiveChange(int count, int V) {
        int Vmod, Vdiv;

        // base case if all coins used and V did not reach 0
        if (count > coins.length-1){
            coinQuantities.clear();
            return 0;
        }
        int current_coin = coins[count];
        if(V >= current_coin){
            Vmod = Math.floorMod(V, current_coin);
            Vdiv = Math.floorDiv(V, current_coin);
            if (Vmod == 0){
                CoinQuantity coinQuantity = new CoinQuantity(current_coin, Vdiv);
                coinQuantities.add(coinQuantity);
                // success as the total has been divided into the coins available
                System.out.println("Success!");
            }else {
                CoinQuantity coinQuantity = new CoinQuantity(current_coin, Vdiv);
                coinQuantities.add(coinQuantity);
                recursiveChange(count+1,Vmod);
            }
        }else{
            recursiveChange(count+1, V);
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (coinQuantities.size()==0){
            return "Change not possible with given coins!";
        }else {
            for (CoinQuantity cq: coinQuantities) {
                sb.append(cq.getValue()).append(" x").append(cq.getQuantity()).append("\n");
            }
            return sb.toString();
        }
    }

    public String getChange(){
        StringBuilder sb = new StringBuilder();
        if (coinQuantities.size()==0){
            // reset the vending machine and display error message on GUI
            return null;
        }else {
            for (CoinQuantity cq: coinQuantities) {
                DecimalFormat df = new DecimalFormat("#0.00");
                double cqDouble = ((double) cq.getValue())/100;
                String stringValue = df.format(cqDouble);
                sb.append(stringValue).append(" x").append(cq.getQuantity()).append("\n");
            }
            return sb.toString();
        }
    }

    public int[] getCoins() {
        return coins;
    }
}
