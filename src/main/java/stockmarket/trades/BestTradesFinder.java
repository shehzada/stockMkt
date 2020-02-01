package stockmarket.trades;

import stockmarket.trades.vo.BestTrades;
import stockmarket.trades.vo.BestTradesComb;

import java.util.stream.Collector;

public class BestTradesFinder {
    Double bestBuy = Double.MAX_VALUE;
    Double bestSell = Double.MIN_VALUE;


    public Double getBestBuy() {
        return bestBuy;
    }


    public Double getBestSell() {
        return bestSell;
    }


    //Accumaltor / logic
    public void accept(Double trades){
        bestBuy = bestBuy < trades ? bestBuy : trades;
        bestSell = bestSell > trades ? bestSell  : trades;
    }

    //Combiner
    public BestTradesFinder combine(BestTradesFinder otherBestTradesFinder){
        bestBuy = bestBuy < otherBestTradesFinder.getBestBuy() ? bestBuy : otherBestTradesFinder.getBestBuy();
        bestSell = bestSell > otherBestTradesFinder.getBestSell() ? bestSell : otherBestTradesFinder.getBestSell();
        return this;
    }

    //Finisher
    public BestTradesComb result(){
      return new BestTradesComb(bestBuy,bestSell);
    }


}
