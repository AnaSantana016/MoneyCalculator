package moneyCalculator.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;
import moneyCalculator.model.Currency;
import moneyCalculator.model.ExchangeCurrency;
import moneyCalculator.model.Money;

public class MoneyCalculator {
    
    public static void main(String[] args) throws Exception{
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.execute();
    }

    private Money money;
    private ExchangeCurrency exchangeCurrency;
    private Currency currencyFrom;
    private Currency currencyTo;
    
    private void execute() throws Exception{
        input();
        process();
        output();
    }

    private void input() {
        System.out.println("Introduzca la cantidad :");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        
        System.out.println("Introduzca la divisa original :");
        currencyFrom = Currency.valueOf(scanner.next().toUpperCase());
        
        money = new Money(amount, currencyFrom);
        
        System.out.println("Introduzca la divisa que quiere :");
        currencyTo = Currency.valueOf(scanner.next().toUpperCase());
    }

    private void process() throws Exception{
        exchangeCurrency = getExchangeCurrency(currencyFrom, currencyTo);
    }

    private void output() {
        System.out.print(money.getAmount() + " " + currencyFrom.getSymbol() 
                        + " equivale a " + money.getAmount() 
                        * exchangeCurrency.getRate() + " " + currencyTo.getSymbol());
    }

    private static ExchangeCurrency getExchangeCurrency(Currency currencyFrom, Currency currencyTo) throws IOException {
        URL url = new URL("http://free.currencyconverterapi.com/api/v5/convert?q="
                            + currencyFrom.getISOCode() + "_" + currencyTo.getISOCode() 
                            + "&compact=y");
        URLConnection connectionURL = url.openConnection();
        
        try (BufferedReader reader = new BufferedReader(
                                     new InputStreamReader(connectionURL.getInputStream()))){
            String line = reader.readLine();
            String subLine = line.substring(line.indexOf(currencyTo.getISOCode())+12,
                             line.indexOf("}"));
            return new ExchangeCurrency(currencyFrom, currencyTo, 
                                        LocalDate.of(2021, Month.FEBRUARY, 1),
                                        Double.parseDouble(subLine));
        }    

    }
}
