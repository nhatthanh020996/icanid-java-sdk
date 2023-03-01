package io.github.galaxyed;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

class HttpRequestUtil {
    static Map<String, Object> sendRequestAndGetBodyRes(HttpURLConnection con, int success_status) throws IOException {
        int responseCode = con.getResponseCode();
        if (responseCode != success_status) {
            throw new IOException("Failed to send a request, response code: " + responseCode);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            Gson gson = new Gson();
            Map<String, Object> userInfo = gson.fromJson(in, Map.class);
            return userInfo;
        }
    }

    private static HttpURLConnection createHttpConnection(String stringUrl, String method) throws IOException {
        URL url = new URL(stringUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        return con;
    }

    static HttpURLConnection createGetHttpConnection(String stringUrl) throws IOException {
        return createHttpConnection(stringUrl, "GET");
    }

    static HttpURLConnection createPostHttpConnection(String stringUrl) throws IOException {
        return createHttpConnection(stringUrl, "POST");
    }
}


