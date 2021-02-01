package moneyCalculator;

import moneyCalculator.persistence.file.FileLoader;
import moneyCalculator.persistence.rest.RestLoader;
import moneyCalculator.persistence.ExchangeCurrencyLoader;
import moneyCalculator.persistence.CurrencyLoader;

public class Main {

    public static void main(String[] args){
        CurrencyLoader currencyLoader = new FileLoader("currencies");
        ExchangeCurrencyLoader loader = new RestLoader();
        
        MoneyCalculator frame = new MoneyCalculator(currencyLoader.currencies());
    }
}
