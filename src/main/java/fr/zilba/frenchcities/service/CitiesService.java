package fr.zilba.frenchcities.service;

import fr.zilba.frenchcities.model.City;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesService {

    private static final String URL = "http://localhost:8181/ville";

    public List<City> getAllCities() throws IOException {
        return this.getAllCities(null, null);
    }

    public List<City> getAllCities(Integer page, String order) throws IOException {
        if (page == null) page = 1;
        URL url;
        if (order != null) {
            url = new URL(URL + "?size=50&page=" + page + "&order=" + order);
        } else {
            url = new URL(URL + "?size=50&page=" + page);
        }
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
        JSONArray jsonArray = new JSONArray(content.toString());
        List<City> cities = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            City city = City.fromJson(json);
            cities.add(city);
        }
        return cities;
    }

    public City getCity(String codeCommune) throws IOException {
        URL url = new URL(URL + "?codeCommune=" + codeCommune);
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
        JSONArray jsonArray = new JSONArray(content.toString());
        if (jsonArray.length() == 0) {
            return null;
        }
        JSONObject json = jsonArray.getJSONObject(0);
        return City.fromJson(json);
    }

    public boolean updateCity(City city) throws IOException {
        URL url = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        // Convertit l'objet City en JSON
        String cityJson = new JSONObject(city).toString();

        // Ajoute le JSON au corps de la requête
        try (OutputStream outputStream = con.getOutputStream()) {
            outputStream.write(cityJson.getBytes());
        }

        int responseCode = con.getResponseCode();
        con.disconnect();
        return String.valueOf(responseCode).startsWith("2");
    }

    public Integer getMaxPage() throws IOException {
        URL url = new URL(URL + "/count");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String response = br.readLine();
            int count = Integer.parseInt(response);
            return (int) Math.ceil((double) count / 50);
        }
    }
}