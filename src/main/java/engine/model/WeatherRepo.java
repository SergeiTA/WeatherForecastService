package engine.model;

import java.util.Map;

public class WeatherRepo implements IWeatherRepo{

    WeatherProvider weatherProvider = new WeatherProvider();

    //TODO implement weather repo logic
    @Override
    public String getForecastRequestsLogs() {
        return null;
    }

    @Override
    public void saveNewForecastRequestLog() {

    }

    @Override
    public String getWeatherForecast(Map<?, ?> location) {
        return weatherProvider.requestWeatherForecast(location);
    }
}
