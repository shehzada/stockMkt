package stockmarket.trades.vo;

/**
 * Created by shehzada on 22/07/2016.
 */
public class BestTrades {
    private Double bestBuy = 0d;
    private Double bestSell = 0d;

    public BestTrades(Double bestBuy, Double bestSell){
        this.bestBuy = bestBuy;
        this.bestSell = bestSell;
    }

    public Double getBestBuy() {
        return bestBuy;
    }

    public Double getBestSell() {
        return bestSell;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BestTrades bestTrades = (BestTrades) o;

        if (!bestBuy.equals(bestTrades.bestBuy)) return false;
        return bestSell.equals(bestTrades.bestSell);

    }

    @Override
    public int hashCode() {
        int result = bestBuy.hashCode();
        result = 31 * result + bestSell.hashCode();
        return result;
    }
}
