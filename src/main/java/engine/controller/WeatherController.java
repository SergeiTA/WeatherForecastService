package engine.controller;

import engine.model.WeatherRepo;
import enums.LocationPropertiesNames;

import java.util.Map;

public class WeatherController implements IWeatherController{

    WeatherRepo weatherRepo = new WeatherRepo();

    @Override
    public String onUserInput(Map<?, ?> location) {

        System.out.printf("Weather forecast fore city %s in %s\n"
                , location.get(LocationPropertiesNames.CITY_NAME).toString()
                , location.get(LocationPropertiesNames.CITY_COUNTRY).toString());

        return weatherRepo.getWeatherForecast(location);
    }

    //TODO create menu for custom forecast requests


}
