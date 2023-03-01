package io.github.galaxyed;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

public class IdSdk {
    private static IdSdk instance;

    private final Auth auth;

    private IdSdk(String clientId, String clientSecret) {
        this.auth = new Auth(clientId, clientSecret);
    }

    public static IdSdk getInstance(String clientId, String clientSecret) {
        if (instance == null) {
            instance = new IdSdk(clientId, clientSecret);
        }
        return instance;
    }

    public Map<String, Object> authAndGetUserInfo(String merchantUserId) throws IOException {
        if (!this.auth.isAuthenticated()) {
            this.auth.authenticate();
        }
        return this.getUserInfo(merchantUserId);
    }

    public Object getGlxUserId(String merchantUserId) throws IOException {
        Map<String, Object> userInfo = this.authAndGetUserInfo(merchantUserId);
        return userInfo.getOrDefault("userId", null);
    }

    public Map<String, Object> authAndGetOtherMerchantUserId(String merchantUserId) throws IOException {
        if (!this.auth.isAuthenticated()) {
            this.auth.authenticate();
        }
        return this.getOtherMerchantUserId(merchantUserId);
    }

    private Map<String, Object> getUserInfo(String merchantUserId) throws IOException {
        String url = Constants.USER_INFO_URL + merchantUserId;
        HttpURLConnection con = HttpRequestUtil.createGetHttpConnection(url);
        System.out.println("Access Token: " + this.auth.getAccessToken());
        con.setRequestProperty("x-api-key", this.auth.getAccessToken());
        Map<String, Object> userInfo = HttpRequestUtil.sendRequestAndGetBodyRes(con, HttpURLConnection.HTTP_OK);
        return userInfo;
    }

    private Map<String, Object> getOtherMerchantUserId(String merchantUserId) throws IOException {
        HttpURLConnection con = HttpRequestUtil.createGetHttpConnection(Constants.MERCHANT_IDS_URL);
        con.setRequestProperty("Authorization", "Bearer " + this.auth.getAccessToken());
        Map<String, Object> merchantIds = HttpRequestUtil.sendRequestAndGetBodyRes(con, HttpURLConnection.HTTP_OK);
        return merchantIds;
    }
}
