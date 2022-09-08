package engine.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.LocationPropertiesNames;
import enums.RequestParams;
import models.Responses.CityForecast;
import models.Responses.CitySearch;
import models.Responses.DailyForecast;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import static utils.PropertiesReader.getProperties;
import static utils.OkHTTPUtil.getHttpClient;

public class WeatherProvider implements IWeatherProvider{

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Response requestWeatherForecast(String cityKey) {
        if (cityKey == null || cityKey.length() ==0) {
            return null;
        }

        Request requestCityForecast = new Request.Builder()
                .get()
                .url(getForecastURL(RequestParams.WEATHER_MICROSERVICE.getRequestParams()).newBuilder()
                        .addPathSegment(RequestParams.FORECAST_PERIOD_DAY.getRequestParams())
                        .addPathSegment(RequestParams.FORECAST_LIMIT_5DAYS.getRequestParams())
                        .addPathSegment(cityKey)
                        .addQueryParameter(RequestParams.FORECAST_APIKEY_REQ_PARAM.getRequestParams()
                        , getProperties().getProperty("APIKey"))
                        .addQueryParameter(RequestParams.QUERY_METRIC_PARAM.getRequestParams(), "true")
                    .build())
                .build();

        try {
            Response responseGetWeatherForecast = getHttpClient().newCall(requestCityForecast).execute();
            errorHandler(responseGetWeatherForecast);
            return responseGetWeatherForecast;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String requestWeatherForecast(Map<?, ?> location) {
        if (location == null || location.isEmpty()) {
            return null;
        }

        Response responseGetWeatherForecast = requestWeatherForecast(findCityKeyListByCountry(location));
        CityForecast cityForecast = null;

        try {
            cityForecast = objectMapper
                    .readValue(Objects.requireNonNull(responseGetWeatherForecast.body()).string(), CityForecast.class);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }

        String commonWeatherForecastOutput =
                "Common forecast for 5 days - " + cityForecast.getHeadline().getText() + "\n";

        StringBuilder dailyTemperatureForecast = new StringBuilder();
        for (DailyForecast dailyForecast:cityForecast.getDailyForecasts()) {
            dailyTemperatureForecast
                    .append("on Date ").append(dailyForecast.getDate())
                    .append(" temperature will be from ").append(dailyForecast.getTemperature().getMinimum().getValue())
                    .append(" to ").append(dailyForecast.getTemperature().getMaximum().getValue())
                    .append(" ").append(dailyForecast.getTemperature().getMaximum().getUnit())
                    .append("\n");
        }

        return commonWeatherForecastOutput + dailyTemperatureForecast;

    }

    private HttpUrl getForecastURL(String microserviceName) {
        return new HttpUrl.Builder()
        .scheme(RequestParams.PROTOCOL_DEFAULT.getRequestParams())
        .host(RequestParams.WEATHER_HOST.getRequestParams())
        .addPathSegment(microserviceName)
        .addPathSegment(RequestParams.API_VERSION_1.getRequestParams())
        .build();
    }

    private Response getCityKeys(Map<?, ?> location) {
        Objects.requireNonNull(location);
        Request requestCityKey = new Request.Builder()
                .get()
                .url(getForecastURL(RequestParams.LOCATION_MICROSERVICE.getRequestParams()).newBuilder()
                        .addPathSegment(RequestParams.CITIES_LOCATION.getRequestParams())
                        .addPathSegment(RequestParams.SEARCH_CALL.getRequestParams())
                        .addQueryParameter(RequestParams.FORECAST_APIKEY_REQ_PARAM.getRequestParams()
                                , getProperties().getProperty("APIKey"))
                        .addQueryParameter(RequestParams.QUERY_SEARCH_PARAM.getRequestParams()
                                , location.get(LocationPropertiesNames.CITY_NAME).toString()).build())
                .build();

        try {
            Response getCityKeyResponse = getHttpClient().newCall(requestCityKey).execute();
            errorHandler(getCityKeyResponse);
            return getCityKeyResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String findCityKeyListByCountry(Map<?, ?> location) {
        Response responseGetCityKey = getCityKeys(location);

        List<CitySearch> citySearches = null;
        try {
            citySearches = objectMapper.readValue(Objects.requireNonNull(responseGetCityKey.body()).string()
                    , new TypeReference<>() {
                    });

            return citySearches.stream().filter(citySearch -> citySearch
                    .getCountry().getEnglishName().equals(location.get(LocationPropertiesNames.CITY_COUNTRY)))
                    .findFirst().get().getKey();
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            System.out.printf("\nCity %s not found in country %s\n"
                    , location.get(LocationPropertiesNames.CITY_NAME)
                    , location.get(LocationPropertiesNames.CITY_COUNTRY));
        }

        return null;
    }


    private void errorHandler(Response response) {
        if (response.code() == 503 || response.code() == 401 || response.code() == 403) {
            throw new RuntimeException("Your request has been declined from server API");
        }
        if (response.code() == 400 || response.code() == 404 || response.code() == 500) {
            throw new RuntimeException("API service in unavailable now, please try again later");
        }
    }

}
