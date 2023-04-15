package fr.zilba.frenchcities.model;

import lombok.Data;
import org.json.JSONObject;

@Data
public class City {

    private String codeCommuneInsee;

    private String nomCommune;

    private String codePostal;

    private String libelleAcheminement;

    private String ligne5;

    private String latitude;

    private String longitude;

    public City(String codeCommuneInsee, String nomCommune, String codePostal, String libelleAcheminement, String ligne5, String latitude, String longitude) {
        this.codeCommuneInsee = codeCommuneInsee;
        this.nomCommune = nomCommune;
        this.codePostal = codePostal;
        this.libelleAcheminement = libelleAcheminement;
        this.ligne5 = ligne5;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static City fromCodeCommune(String codeCommune) {
        return new City(codeCommune, "", "", "", "", "", "");
    }

    public static City fromJson(JSONObject json) {
        String codeCommuneInsee = json.getString("codeCommuneInsee");
        String nomCommune = json.getString("nomCommune");
        String codePostal = json.getString("codePostal");
        String libelleAcheminement = json.getString("libelleAcheminement");
        String ligne5 = json.getString("ligne5");
        String latitude = json.getString("latitude");
        String longitude = json.getString("longitude");
        return new City(codeCommuneInsee, nomCommune, codePostal, libelleAcheminement, ligne5, latitude, longitude);
    }

    @Override
    public String toString() {
        return "City [codeCommune=" + codeCommuneInsee + ", nomCommune=" + nomCommune + ", codePostal=" + codePostal
                + ", libelleAcheminement=" + libelleAcheminement + ", ligne5=" + ligne5 + ", latitude=" + latitude
                + ", longitude=" + longitude + "]";
    }
}
