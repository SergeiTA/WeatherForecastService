import engine.model.WeatherProvider;
import engine.view.UserInterface;
import enums.RequestParams;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

import static utils.OkHTTPUtil.getHttpClient;
import static utils.PropertiesReader.getProperties;


public class Main {

    public static void main(String[] args) {

//        HttpUrl httpUrl = new HttpUrl.Builder()
//                .scheme(RequestParams.PROTOCOL_DEFAULT.getRequestParams())
//                .host(RequestParams.WEATHER_HOST.getRequestParams())
//                .addPathSegment(RequestParams.WEATHER_MICROSERVICE.getRequestParams())
//                .addPathSegment(RequestParams.API_VERSION_1.getRequestParams())
//                .addPathSegment(RequestParams.FORECAST_PERIOD_DAY.getRequestParams())
//                .addPathSegment(RequestParams.FORECAST_LIMIT_5DAYS.getRequestParams())
//                .addPathSegment(getProperties().getProperty("CityKeyRnD"))
//                .addQueryParameter(RequestParams.FORECAST_APIKEY_REQ_PARAM.getRequestParams()
//                        , getProperties().getProperty("APIKey"))
//                .build();
//
//        System.out.println("---------------");
//        System.out.println(httpUrl);
//        System.out.println("---------------");
//
//        Request request = new Request.Builder()
//                .get()
//                .url(httpUrl)
//                .build();
//
//
//        try {
//            Response response = getHttpClient().newCall(request).execute();
//            System.out.println(Objects.requireNonNull(response.body()).string());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        UserInterface userInterface = new UserInterface();
        userInterface.showUI();

//        WeatherProvider weatherProvider = new WeatherProvider();
//
//        HttpUrl httpUrl = weatherProvider.getForecastURL(RequestParams.WEATHER_MICROSERVICE.getRequestParams());
//        System.out.println("httpUrl = " + httpUrl);
//        System.out.println("-----------------");
//
//
//        httpUrl = httpUrl.newBuilder()
//                .addPathSegment(RequestParams.FORECAST_PERIOD_DAY.getRequestParams())
//                .addPathSegment(RequestParams.FORECAST_LIMIT_5DAYS.getRequestParams())
//                .addPathSegment(getProperties().getProperty("CityKeyRnD"))
//                .addQueryParameter(RequestParams.FORECAST_APIKEY_REQ_PARAM.getRequestParams()
//                        , getProperties().getProperty("APIKey"))
//                .build();
//
//        System.out.println("httpUrl = " + httpUrl);
//
//        httpUrl = httpUrl.newBuilder()
//                .addPathSegment(RequestParams.CITIES_LOCATION.getRequestParams())
//                .addPathSegment(RequestParams.SEARCH_CALL.getRequestParams())
//                .addQueryParameter(RequestParams.FORECAST_APIKEY_REQ_PARAM.getRequestParams()
//                        , getProperties().getProperty("APIKey"))
//                .addQueryParameter("q"
//                        , "Moscow")
//                .build();
//
//        System.out.println("httpUrl = " + httpUrl);

    }


}
