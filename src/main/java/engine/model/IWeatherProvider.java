package engine.model;

import okhttp3.Response;

import java.util.Map;

public interface IWeatherProvider {

    Response requestWeatherForecast(String cityKey);

}
