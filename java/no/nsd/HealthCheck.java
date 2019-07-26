package no.nsd;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HealthCheck {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Must specify healthcheck URL as first argument");
            System.exit(255);
        }
        String urlString = args[0].trim();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(1000);
            connection.connect();
            int code = connection.getResponseCode();
            try {
                connection.disconnect();
            } catch (Exception e) {
                //
            }
            if (code == 200) {
                System.exit(0);
            } else {
                System.exit(1);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.err.println("Malformed url: " + urlString + ", :" + e.getMessage());
            System.exit(255);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("IOException: " + e.getMessage() + " for URL: " + urlString);
            System.exit(1);
        }
    }
}
