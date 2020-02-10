package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WeatherGetter {

    public static void weatherGetter() throws IOException {

        BufferedReader br = null;

        try {
            String vancouverweatherquery = "https://api.openweathermap.org/data/2.5/weather?q=Vancouver,CA&APPID=";
            String theURL = vancouverweatherquery + "d2227c075ea2ea91dc18bbbd6063ce5d";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }
}

