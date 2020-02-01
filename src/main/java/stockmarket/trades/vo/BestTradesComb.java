package stockmarket.trades.vo;

public class BestTradesComb {
    private Double bestBuy;
    private Double bestSell;

    public BestTradesComb(Double bestBuy, Double bestSell){
        this.bestBuy = bestBuy;
        this.bestSell = bestSell;
    }

    public Double getBestBuy() {return bestBuy; }

    public Double getBestSell() { return bestSell; }
}
