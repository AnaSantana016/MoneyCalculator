package moneyCalculator.persistence.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import moneyCalculator.model.Currency;
import moneyCalculator.model.ExchangeCurrency;
import moneyCalculator.persistence.ExchangeCurrencyLoader;

public class RestLoader implements ExchangeCurrencyLoader{

    @Override
    public ExchangeCurrency load(Currency currencyFrom, Currency currencyTo) {
        try{
            return new ExchangeCurrency(currencyFrom, currencyTo, 
                                        read(currencyFrom.getISOCode(), currencyTo.getISOCode())); 
        }
        catch(IOException e){
            return null;
        }
    }

    private double read(String currencyFrom, String currencyTo) throws IOException{
        String line = read(new URL("https://api.fiexr.io/latest?base=" 
                            + currencyFrom + "&symbols=" + currencyTo));
        return Double.parseDouble(line.substring(line.indexOf(currencyTo)+5, line.indexOf("}")));
    }
    
    private String read(URL url) throws IOException{
        InputStream is = url.openStream();
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        return new String(buffer,0,length);
    }
    
}
