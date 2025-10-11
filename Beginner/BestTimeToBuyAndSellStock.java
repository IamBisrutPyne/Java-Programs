// File: BestTimeToBuyAndSellStock.java
// Description: Implementation of the "Best Time to Buy and Sell Stock" algorithm (LeetCode #121)

public class BestTimeToBuyAndSellStock {

    /**
     * Finds the maximum profit that can be achieved from a single buy and sell.
     * 
     * @param prices array representing the stock price on each day
     * @return maximum profit achievable (0 if no profit is possible)
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int minPrice = prices[0];  // Initially assume first price is the minimum
        int maxProfit = 0;         // Start with zero profit

        for (int i = 1; i < prices.length; i++) {
            // If we find a smaller price, update minPrice
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // Else, calculate profit if we sell today
                int profit = prices[i] - minPrice;
                // Update maxProfit if this is the highest profit so far
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }

        return maxProfit;
    }

    // Main method to test the code
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Maximum Profit: " + solution.maxProfit(prices)); 
        // Output should be 5 (Buy at 1, Sell at 6)
    }
}
