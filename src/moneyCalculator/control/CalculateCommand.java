package moneyCalculator.control;

import moneyCalculator.model.Currency;
import moneyCalculator.model.Money;
import moneyCalculator.persistence.ExchangeCurrencyLoader;
import moneyCalculator.ui.MoneyDialog;
import moneyCalculator.ui.MoneyDisplay;

public class CalculateCommand implements Command{
    
    private final MoneyDialog moneyDialog;
    private final MoneyDisplay moneyDisplay;
    private final ExchangeCurrencyLoader loader;
    private Currency eur = new Currency("EUR", "EURO", "€");

    public CalculateCommand(MoneyDialog moneyDialog, MoneyDisplay moneyDisplay, ExchangeCurrencyLoader loader) {
        this.moneyDialog = moneyDialog;
        this.moneyDisplay = moneyDisplay;
        this.loader = loader;
    }
    
    @Override
    public String name() {
        return "Calcular";
    }

    @Override
    public void execute() {
        moneyDisplay.display(exchange(moneyDialog.get()));
    }
    
    private Money exchange(Money money){
        return new Money(money.getAmount() * rateOf(money.getCurrency()), eur);
    }

    private double rateOf(Currency currency) {
        return loader.load(currency, eur).getRate();
    }
}
