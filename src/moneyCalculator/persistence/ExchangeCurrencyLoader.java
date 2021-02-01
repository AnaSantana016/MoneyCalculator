package moneyCalculator.persistence;

import moneyCalculator.model.Currency;
import moneyCalculator.model.ExchangeCurrency;

public interface ExchangeCurrencyLoader {
    ExchangeCurrency load(Currency currencyFrom, Currency currencyTo);
}
