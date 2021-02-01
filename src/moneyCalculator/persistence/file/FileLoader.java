package moneyCalculator.persistence.file;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import moneyCalculator.model.Currency;
import moneyCalculator.persistence.CurrencyLoader;

public class FileLoader implements CurrencyLoader{

    private final String name;

    public FileLoader(String name) {
        this.name = name;
    }
    
    @Override
    public Currency[] currencies() {
        
        List<Currency> list = new ArrayList<>();
        try{
            BufferedReader reade = new BufferedReader(
                                    new FileReader(new File(name)));
            while(true){
                String line = reade.readLine();
                if (line == null) break;
                list.add(currencyOf(line));
            }
        }
        catch(IOException e){
        }
        return list.toArray(new Currency[0]);
    }
    
    private Currency currencyOf(String line){
        String[] split = line.split(",");
        return new Currency(split[0], split[1], split[2]);
    }
    
}
