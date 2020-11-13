import java.lang.reflect.Array;
import java.util.HashMap;

public class ChangeCalculation {
    private int[] coins;

    public ChangeCalculation() {
        // 1=one penny, 100=one pound...
        coins = new int[]{2000, 1000, 500, 100, 50, 10 ,5 ,1};
    }


    public static int getChange(int[] coins, int m, int V) {
        return 0;
    }

    public int[] getCoins() {
        return coins;
    }

    public static void main(String[] args) {
        int[] coins = {15,10,5,1};
        int V = 15;
        System.out.println("Minimum coins required is "+ ChangeCalculation.getChange(coins, coins.length, V));

    }
}
