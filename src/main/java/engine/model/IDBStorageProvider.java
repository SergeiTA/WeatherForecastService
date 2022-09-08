package engine.model;

import okhttp3.Request;
import okhttp3.Response;

public interface IDBStorageProvider {

    String readForecastRequestsLogs();

    String writeNewForecastRequestLog(Request request, Response response);


}
