import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChangeCalculation {
    private final int[] coins;
    private ArrayList<CoinQuantity> coinQuantities;

    public ChangeCalculation() {
        // 1=one penny, 100=one pound...
        coins = new int[]{2000,1000,100,10,5,1};
        coinQuantities = new ArrayList<>();
    }


    /**
     * takes the amount V and recursively mods then divides to find the remainder and the amount of times
     * the coin goes into V until a remainder of 0 or count goes beyond the length of the coin array.
     *
     * @param count
     * @param V
     * @return
     */
    public void recursiveChange(int count, int V) {
        int Vmod, Vdiv;

        // base case if all coins used and V did not reach 0
        if (count > coins.length-1){
            coinQuantities.clear();
        }
        // setting the current_coin to the coin at location count
        int current_coin = coins[count];

        // check if the current coin can be divided by V to give 1 or more
        if(V >= current_coin){
            // finds the remainder of V and the current_coin
            Vmod = Math.floorMod(V, current_coin);
            // finds division of V and current_coin which is the whole amount of times e.g. 123 div 100 = 1
            Vdiv = Math.floorDiv(V, current_coin);

            // if the remainder is 0 then V has been divided into the coins available
            if (Vmod == 0){
                // add final coin and amount
                CoinQuantity coinQuantity = new CoinQuantity(current_coin, Vdiv);
                coinQuantities.add(coinQuantity);
            }else {
                // add coin and quantity
                CoinQuantity coinQuantity = new CoinQuantity(current_coin, Vdiv);
                coinQuantities.add(coinQuantity);
                // there is still a remainder left so recursiveChange is called again.
                recursiveChange(count+1,Vmod);
            }
        }else{
            // coin too large to divide recursiveChange is called again.
            recursiveChange(count+1, V);
        }
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
