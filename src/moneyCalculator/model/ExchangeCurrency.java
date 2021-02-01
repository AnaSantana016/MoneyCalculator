package moneyCalculator.model;

public class ExchangeCurrency {

    private final Currency from;
    private final Currency to;
    private final double rate;

    public ExchangeCurrency(Currency from, Currency to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public Currency getFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }
    
    public double getRate() {
        return rate;
    }
    
    
}
