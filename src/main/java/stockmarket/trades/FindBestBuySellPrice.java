package stockmarket.trades;


import stockmarket.trades.vo.BestTrades;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by shehzada on 22/07/2016.
 */
public class FindBestBuySellPrice {

    public Map<String, BestTrades> getBestTradePrices(List<Double> tradeList) {
        long startTime = System.nanoTime();
        Map<String, BestTrades> results = new HashMap<>(1);
        IntStream.range(0, tradeList.size())
                .reduce((a, b) -> tradeList.get(a) > tradeList.get(b) ? b : a)
                .ifPresent(bestBuy -> {
                    OptionalInt opt = IntStream.range(bestBuy + 2, tradeList.size())
                            .reduce((c, d) -> tradeList.get(c) < tradeList.get(d) ? d : c);
                    opt.ifPresent(bestSell -> results.put("bestBuySellPrices", (new BestTrades(tradeList.get(bestBuy), tradeList.get(bestSell)))));
                });
        long endTime = System.nanoTime() - startTime;
        System.out.println(" time take " + (System.nanoTime() - startTime) / 1000000 + " millisec");
        return results;
    }

    public Map<String, BestTrades> getBestTradePrices2(List<Double> tradeList) {
        long startTime = System.nanoTime();
        Map<String, BestTrades> results = new HashMap<>(1);
       Optional<Double> bb =   tradeList.stream().max(Comparator.comparing(Double::valueOf));
       Optional<Double> bs =      tradeList.stream().min(Comparator.comparing(Double::valueOf));
        results.put("bestBuySellPrices",new BestTrades(bb.get(),bs.get()));
        long endTime = System.nanoTime() - startTime;
        System.out.println(" time take " + (System.nanoTime() - startTime) / 1000000 + " millisec");
        return results;
    }


    public Map<String, BestTrades> getBestTradePricesBoxedVersion(List<Double> tradeList) {
        long startTime = System.nanoTime();
        Map<String, BestTrades> results = new HashMap<>(1);
        IntStream.range(0, tradeList.size())
                .boxed()
                .min(Comparator.comparing(tradeList::get))
                .ifPresent(bestBuy -> {
                    IntStream.range(bestBuy + 2, tradeList.size())
                            .boxed()
                            .max(Comparator.comparing(tradeList::get))
                            .ifPresent(bestSell -> {
                                results.put("bestBuySellPrices", (new BestTrades(tradeList.get(bestBuy), tradeList.get(bestSell))));
                            });
                });
        long endTime = System.nanoTime() - startTime;
        System.out.println("Boxed version time take " + (System.nanoTime() - startTime) / 1000000 + " millisec");
        return results;
    }
}
