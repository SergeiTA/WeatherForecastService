package engine.model;

import java.util.Map;

public interface IWeatherRepo {

    String getForecastRequestsLogs();

    void saveNewForecastRequestLog();

    String getWeatherForecast(Map<?, ?> location);

}
