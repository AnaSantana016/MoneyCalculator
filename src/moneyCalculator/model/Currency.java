package moneyCalculator.model;

public enum Currency {

    EUR("EUR","euros","€"),
    GBP("GBP","libras","£"),
    USD("USD","dolares","$");
    
    //atributos de la divisa
    private String currencyName;
    private String symbol;
    private String ISOCode;

    private Currency(String currencyName, String symbol, String ISOCode) {
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
}
