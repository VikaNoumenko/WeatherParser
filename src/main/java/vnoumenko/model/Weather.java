package vnoumenko.model;

import java.util.Date;

/**
 * 17.10.2017
 * Weather
 *
 * @author Victoria Noumenko
 * @version v1.0
 */

public class Weather {

    String city;

    Integer temperature;

    public String getCity() {

        return city;
    }

    public void setCity(String city) {

        this.city = city;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {

        this.temperature = temperature;
    }


}

