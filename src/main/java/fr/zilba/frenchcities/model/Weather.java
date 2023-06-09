package fr.zilba.frenchcities.model;

import lombok.Data;
import org.json.JSONObject;

@Data
public class Weather {


    private String description;

    private String icon;

    private double temperature;

    public static Weather fromJson(JSONObject json) {
        Weather weather = new Weather();
        weather.setDescription(json.getString("description"));
        weather.setIcon(json.getString("icon"));
        return weather;
    }

    public String getWeather() {
        return this.description.substring(0, 1).toUpperCase() + this.description.substring(1);
    }

    public String getIconURL() {
        return "https://openweathermap.org/img/wn/" + this.icon + "@2x.png";
    }

    @Override
    public String toString() {
        return "Weather{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
