package enums;

public enum RequestParams {
    PROTOCOL_DEFAULT ("http"),
    WEATHER_HOST ("dataservice.accuweather.com"),
    WEATHER_MICROSERVICE ("forecasts"),
    LOCATION_MICROSERVICE ("locations"),
    API_VERSION_1 ("v1"),
    FORECAST_PERIOD_DAY ("daily"),
    CITIES_LOCATION ("cities"),
    FORECAST_LIMIT_5DAYS ("5day"),
    SEARCH_CALL ("search"),
    QUERY_SEARCH_PARAM("q"),
    QUERY_METRIC_PARAM("metric"),
    FORECAST_APIKEY_REQ_PARAM("apikey");

    private final String requestParams;

    RequestParams (String requestParams) {this.requestParams = requestParams;}

    public String getRequestParams() {
        return requestParams;
    }
}
