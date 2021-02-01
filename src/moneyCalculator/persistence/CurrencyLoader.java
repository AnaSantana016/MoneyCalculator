package moneyCalculator.persistence;

import moneyCalculator.model.Currency;

public interface CurrencyLoader {
    Currency[] currencies();
}
