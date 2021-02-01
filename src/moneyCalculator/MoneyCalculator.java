package moneyCalculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneyCalculator.ui.swing.SwingMoneyDialog;
import moneyCalculator.ui.swing.SwingMoneyDisplay;
import moneyCalculator.control.Command;
import moneyCalculator.model.Currency;
import moneyCalculator.ui.MoneyDialog;
import moneyCalculator.ui.MoneyDisplay;

public class MoneyCalculator extends JFrame {
    
    private MoneyDialog moneyDialog;
    private MoneyDisplay moneyDisplay;
    private final Map<String,Command> commands = new HashMap<>();
    private Currency[] currencies;

    public MoneyCalculator(Currency[] currencies) {
        this.currencies = currencies;
        this.setTitle("MONEY CALCULATOR");
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.add(moneyDialog(), BorderLayout.NORTH);
        this.add(moneyDisplay(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    
    public void add(Command command){
        commands.put(command.name(), command);
    }
    
    private MoneyDialog getMoneyDialog(){
        return moneyDialog;
    }
    
    private MoneyDisplay getMoneyDisplay(){
        return moneyDisplay;
    }
    
    private Component moneyDialog(){
        SwingMoneyDialog dialog = new SwingMoneyDialog(currencies);
        moneyDialog = dialog;
        return dialog;
    }
    
    private Component moneyDisplay(){
        SwingMoneyDisplay display = new SwingMoneyDisplay();
        moneyDisplay = display;
        return display;
    }
    
    private Component toolbar(){
        JPanel panel = new JPanel();
        panel.add(calculateButton());
        return panel;
    }
    
    private JButton calculateButton(){
        JButton button = new JButton("Calculate");
        button.addActionListener(calculate());
        return button;
    }
    
    private ActionListener calculate(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("Calculate").execute();    
            }
        };
    }
}
