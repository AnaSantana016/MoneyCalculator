package moneyCalculator.ui.swing;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import moneyCalculator.model.Currency;
import moneyCalculator.model.Money;
import moneyCalculator.ui.MoneyDialog;


public class SwingMoneyDialog extends JPanel implements MoneyDialog{
    
    private final Currency[] currencies;
    private Currency currency;
    private String amount;

    public SwingMoneyDialog(Currency[] currencies) {
        this.currencies = currencies;
        this.add(amount());
        this.add(currency());
    }
    
    @Override
    public Money get(){
        return new Money(Double.parseDouble(amount), currency);
    }
    
    private Component amount(){
        JTextField textField = new JTextField("100");
        textField.setColumns(10);
        textField.getDocument().addDocumentListener(amountChanged());
        amount = textField.getText();
        return textField;
    }
    
    private Component currency(){
        JComboBox combo = new JComboBox(currencies);
        combo.addItemListener(currencyChanged());
        currency = (Currency) combo.getSelectedItem();
        return combo;
    }
    
    private ItemListener currencyChanged(){
        return new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event){
                if(event.getStateChange() == ItemEvent.DESELECTED){
                    currency = (Currency) event.getItem();
                }
            }
        };
    }
    
    private DocumentListener amountChanged(){
        return new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent event){
                amountChanged(event.getDocument());
            }
            
            @Override
            public void removeUpdate(DocumentEvent event){
                amountChanged(event.getDocument());
            }
            
            @Override
            public void changedUpdate(DocumentEvent event){
                amountChanged(event.getDocument());
            }
            
            private void amountChanged(Document document){
                try{
                    amount = document.getText(0, document.getLength());
                }
                catch(BadLocationException e){
                }
            }
        };
    }
}
