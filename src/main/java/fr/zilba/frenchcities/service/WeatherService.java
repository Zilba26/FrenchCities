package fr.zilba.frenchcities.service;

import fr.zilba.frenchcities.model.City;
import fr.zilba.frenchcities.model.Weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class WeatherService {

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?lang=fr&units=metric";
    private static final String API_KEY = "542895e1fae55e549f162f941863cbc4";

    public Weather getWeather(String longitude, String latitude) throws IOException {
        if (longitude == null || latitude == null || longitude.isEmpty() || latitude.isEmpty()) {
            return null;
        }
        URL url = new URL(URL + "&lat=" + latitude + "&lon=" + longitude + "&APPID=" + API_KEY);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        JSONObject json = new JSONObject(content.toString());
        JSONArray weathers = json.getJSONArray("weather");
        double temperature = json.getJSONObject("main").getDouble("temp");
        Weather weather = Weather.fromJson(weathers.getJSONObject(0));
        weather.setTemperature(temperature);
        return weather;
    }

    public Weather getWeather(City city) throws IOException {
        return getWeather(city.getLongitude(), city.getLatitude());
    }
}
