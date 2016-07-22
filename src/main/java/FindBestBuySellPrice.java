import stockmarket.trades.vo.BestTrades;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

/**
 * Created by shehzada on 22/07/2016.
 */
public class FindBestBuySellPrice {



    public static void main(String args[]) {


        //List<Double> tradeList = Arrays.asList(19.35, 19.30, 18.88, 18.93, 18.95, 19.03, 19.00, 18.97, 18.97, 18.98);
        List<Double> tradeList = Arrays.asList(9.20, 8.03, 10.02, 8.08, 8.14, 8.10, 8.31, 8.28, 8.35, 8.34, 8.39, 8.45, 8.38, 8.38, 8.32, 8.36, 8.28, 8.28, 8.38, 8.48, 8.49, 8.54, 8.73, 8.72, 8.76, 8.74, 8.87, 8.82, 8.81, 8.82, 8.85, 8.85, 8.86, 8.63, 8.70, 8.68, 8.72, 8.77, 8.69, 8.65, 8.70, 8.98, 8.98, 8.87, 8.71, 9.17, 9.34, 9.28, 8.98, 9.02, 9.16, 9.15, 9.07, 9.14, 9.13, 9.10, 9.16, 9.06, 9.10, 9.15, 9.11, 8.72, 8.86, 8.83, 8.70, 8.69, 8.73, 8.73, 8.67, 8.70, 8.69, 8.81, 8.82, 8.83, 8.91, 8.80, 8.97, 8.86, 8.81, 8.87, 8.82, 8.78, 8.82, 8.77, 8.54, 8.32, 8.33, 8.32, 8.51, 8.53, 8.52, 8.41, 8.55, 8.31, 8.38, 8.34, 8.34, 8.19, 8.17, 8.16);

        FindBestBuySellPrice fb = new FindBestBuySellPrice();
        ConcurrentMap<String,BestTrades> results = fb.getBestTradePrices(tradeList);
        BestTrades bt = results.get("bestBuySellPrices");
        System.out.println("best prices - buy " + bt.getBestBuy() + " best sell " + bt.getBestSell());

    }

    public ConcurrentMap<String,BestTrades> getBestTradePrices(List<Double> tradeList) {
        long startTime = System.nanoTime();
        ConcurrentMap<String,BestTrades> results = new ConcurrentHashMap<>(2);
        IntStream.range(0, tradeList.size())
                .reduce((a, b) -> tradeList.get(a) > tradeList.get(b) ? b : a)
                .ifPresent(bestBuy -> {
                    OptionalInt opt =  IntStream.range(bestBuy + 2, tradeList.size())
                            .reduce((c, d) -> tradeList.get(c) < tradeList.get(d) ? d : c);
                    opt.ifPresent(bestSell -> results.put("bestBuySellPrices",(new BestTrades(tradeList.get(bestBuy), tradeList.get(bestSell)))));
                });
        long endTime = System.nanoTime() - startTime;
        System.out.println(" time take " + endTime / 1000 + " ms");
        return results;
    }
}
