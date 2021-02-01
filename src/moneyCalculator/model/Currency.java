package moneyCalculator.model;

public class Currency {

    private final String currencyName;
    private final String symbol;
    private final String ISOCode;

    public Currency(String currencyName, String symbol, String ISOCode) {
        this.currencyName = currencyName;
        this.symbol = symbol;
        this.ISOCode = ISOCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getISOCode() {
        return ISOCode;
    }

    @Override
    public String toString() {
        return ISOCode;
    }
    
    
}
