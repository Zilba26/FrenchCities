package fr.zilba.frenchcities.service;

import fr.zilba.frenchcities.model.City;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitiesService {

    private static final String URL = "http://localhost:8181/ville";
    public List<List<City>> getAllCitiesPaginees() throws IOException {
        URL url = new URL(URL);
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
        List<List<City>> cities = new ArrayList<>();
        cities.add(new ArrayList<>());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            City city = City.fromJson(json);
            if (cities.get(cities.size() - 1).size() == 50) {
                cities.add(new ArrayList<>());
            }
            cities.get(cities.size() - 1).add(city);
        }
        return cities;
    }

    public List<City> getAllCities() throws IOException {
        URL url = new URL(URL);
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
        JSONObject json = jsonArray.getJSONObject(0);
        return City.fromJson(json);
    }
}
