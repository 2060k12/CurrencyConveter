package LGMVIP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class AppGui implements ActionListener {
    // making the variables

    JComboBox<String> fromOption, toOption;
    JTextField amountField, convertTextField;
    float convertedAmount;
    JLabel toLabel, fromLabel;


    void createGui() {
        // Frame for our app
        JFrame frame = new JFrame("Currency Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(340, 420);
        frame.setLayout(null);

        // making the fonts
        Font h1 = new Font("Arial", Font.BOLD, 25);
        Font h2 = new Font("Arial", Font.PLAIN, 20);

        // making the lable which says welcome on top of the screen
        JLabel welcomeLabel = new JLabel("Welcome,");
        frame.add(welcomeLabel);
        welcomeLabel.setBounds(20, 20, 500, 50);
        welcomeLabel.setFont(h1);

        // making the lable which says currency convert from
        fromLabel = new JLabel("Currency Convert From:");
        fromLabel.setBounds(20, 60, 500, 50);
        fromLabel.setFont(h2);
        frame.add(fromLabel); //adding it to the frame


        //making a combo box for the currency options
        fromOption = new JComboBox<>();
        fromOption.addItem("USD");
        fromOption.addItem("EUR");
        fromOption.addItem("CAD");
        fromOption.addItem("INR");
        fromOption.addItem("AUD");
        frame.add(fromOption); //adding it to the frame
        fromOption.setBounds(20, 100, 300, 50);


        // making the text field for the amount
        amountField = new JTextField();
        amountField.setBounds(20, 140, 300, 50);
        amountField.setFont(h2);
        frame.add(amountField); //adding it to the frame

        toLabel = new JLabel("Currency Convert to:");
        toLabel.setBounds(20, 200, 500, 50);
        toLabel.setFont(h2);
        frame.add(toLabel); //adding it to the frame

        //making a combo box for the currency options
        toOption = new JComboBox<>();
        toOption.addItem("USD");
        toOption.addItem("EUR");
        toOption.addItem("CAD");
        toOption.addItem("INR");
        toOption.addItem("AUD");
        toOption.setBounds(20, 230, 300, 50);
        frame.add(toOption); // adding it to the frame

        // this is where the converted amount will be displayed
        convertTextField = new JTextField("");
        convertTextField.setBounds(20, 270, 300, 50);
        convertTextField.setFont(h2);
        convertTextField.setEditable(false); // making it so that the user can't edit the text field
        frame.add(convertTextField); //adding it to the frame

        // this is where the converted amount will be displayed
        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(20, 330, 300, 50);
        convertButton.setFont(h2);
        frame.add(convertButton);

        // adding action listener to the button
        fromOption.addActionListener(this);
        convertButton.addActionListener(this);
        toOption.addActionListener(this);
        convertTextField.addActionListener(this);

        frame.setVisible(true); // making the frame visible
    }


    @Override
    // this is where the action listener is used
    public void actionPerformed(ActionEvent e) {

        // getting the button pressed
        String buttonPressed = e.getActionCommand();

        // getting the selected item from the combo box
        Main.fromCode = (String) fromOption.getSelectedItem();
        Main.toCode = (String) toOption.getSelectedItem();

        // if the button pressed is convert then it will convert the amount
        if (buttonPressed.equals("Convert")) {
            convertedAmount = Float.parseFloat(amountField.getText());
            try {
                Main.sendHttpGetRequest(Main.fromCode, Main.toCode, convertedAmount);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        }
    }
}
