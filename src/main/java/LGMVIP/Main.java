package LGMVIP;

import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {

    static String fromCode, toCode;
    static AppGui appGui = new AppGui();

    // main method
    public static void main(String[] args) throws IOException {
        appGui.createGui();

    }

    // method to send the http request
    public static void sendHttpGetRequest(String fromCode, String toCode, double amount) throws IOException {
        // making the url
        String Get_URL = "https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_TsqCDZVGnDTCtOlKBpwaqRbcPw3yfGHkOKfuZ50c&currencies=" + toCode + "&base_currency=" + fromCode;
        URL url = new URL(Get_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        // if the response code is 200 then we will read the response
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine); //for debugging
                response.append(inputLine);
            }
            in.close();

            JSONObject obj = new JSONObject(response.toString());
            double exchangeRate = obj.getJSONObject("data").getDouble(toCode);
            String sExchangeRate = String.format("%.2f", exchangeRate); // converting the exchange rate to string with 2 decimal places
            appGui.toLabel.setText(("Currency Convert to: @ " + sExchangeRate));

            String sAmount = String.format("%.2f", (amount * exchangeRate)); // converting the amount to string with 2 decimal places
            appGui.convertTextField.setText(sAmount);  // setting the converted amount to the text field
        }
        // if the response code is not 200 then we will show an error message
        else {
            JOptionPane optionPane = new JOptionPane("Error: " + String.valueOf(responseCode), JOptionPane.ERROR_MESSAGE);
        }


    }
}