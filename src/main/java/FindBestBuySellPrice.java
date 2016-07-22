package stockmarket.trades;


import stockmarket.trades.vo.BestTrades;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Created by shehzada on 22/07/2016.
 */
public class FindBestBuySellPrice {

    public Map<String,BestTrades> getBestTradePrices(List<Double> tradeList) {
        long startTime = System.nanoTime();
        Map<String,BestTrades> results = new HashMap<>(1);
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
