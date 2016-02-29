package co.rcycl.rcycl;

import java.net.HttpURLConnection;
import java.net.URL;

// Created by Whit on 2/28/2016.
// A class to prepare and manage requests to and from the API server
public class APIManager {

    public static final String HTTP_GET = "GET";
    public static final String HTTP_POST = "POST";

    // Please refer to the rcycl API on GitHub.
    public static final String API_ROOT = "http://rcycl.herokuapp.com/v1/";
    public static final String API_LOGIN = "http://rcycl.herokuapp.com/v1/users/login";

    public static String AuthToken = null;

    public enum UserPermissions {
        CUSTOMER,
        VENDOR,
        VENDOR_ADMIN,
        ADMIN
    }

    public static HttpURLConnection initAuth() {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(API_LOGIN);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setRequestMethod(APIManager.HTTP_POST);
        } catch (Exception e) {
            Utils.log(connection, e.getLocalizedMessage());
        }

        return connection;
    }

    public static HttpURLConnection initRequest(String requestMethod, String endpoint, int Id) {
        HttpURLConnection connection = null;

        // Some API requests require an ID tacked to the end of the URL.
        // By convention, 0 is used as a placeholder for "null" ID's.
        if (Id != 0) {
            endpoint += "/" + String.valueOf(Id);
        }

        try {
            URL url = new URL(API_ROOT + endpoint);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("x-auth-token", AuthToken);

            connection.setRequestMethod(requestMethod);
        } catch (Exception e) {
            Utils.log(connection, e.getLocalizedMessage());
        }

        return connection;
    }
}