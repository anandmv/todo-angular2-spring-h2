package com.todo.jpa.controller.util;
import org.springframework.http.HttpHeaders;
/**
 * Created by anand on 27/10/17.
 */
public class HeaderUtil {


    public static HttpHeaders createAlert(String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-projectTemplate1App-alert", message);
        headers.add("X-projectTemplate1App-params", param);
        return headers;
    }

    public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
        return createAlert("todoApp." + entityName + ".created", param);
    }

    public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
        return createAlert("todoApp." + entityName + ".updated", param);
    }

    public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
        return createAlert("todoApp." + entityName + ".deleted", param);
    }

    public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-projectTemplate1App-error", "error." + errorKey);
        headers.add("X-projectTemplate1App-params", entityName);
        return headers;
    }
}


