package com.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class app extends JFrame {
    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JButton convertButton;
    private JLabel resultLabel;

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/d128ab7539f9553a3ea0bbea/latest/";

    public app() {
        setTitle("Currency Converter");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        amountField = new JTextField(10);
        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR", "SAR"});
        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "INR", "SAR"});
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("From:"));
        panel.add(fromCurrency);
        panel.add(new JLabel("To:"));
        panel.add(toCurrency);
        panel.add(convertButton);
        panel.add(resultLabel);

        add(panel);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String amount = amountField.getText();
        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();

        try {
            double amountValue = Double.parseDouble(amount);
            double rate = getExchangeRate(from, to);
            double result = amountValue * rate;
            resultLabel.setText("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    private double getExchangeRate(String from, String to) throws Exception {
        String urlString = API_URL + from;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        conn.disconnect();

        String jsonResponse = content.toString();
        return parseRate(jsonResponse, to);
    }

    private double parseRate(String jsonResponse, String to) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONObject rates = jsonObject.getJSONObject("conversion_rates");
        return rates.getDouble(to);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new app().setVisible(true);
            }
        });
    }
}
