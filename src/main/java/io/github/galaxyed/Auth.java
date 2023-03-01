package io.github.galaxyed;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.util.Base64;
import java.util.Map;

public class Auth {
    private final String clientId;
    private final String clientSecret;
    private ClientCredentials credentials = ClientCredentials.getNullCredential();

    public Auth(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getAccessToken() {
        return this.credentials.accessToken;
    }

    public ClientCredentials authenticate() throws IOException {
        String credentials = this.clientId + ":" + this.clientSecret;
        HttpURLConnection con = HttpRequestUtil.createPostHttpConnection(Constants.CREDENTIAL_CLIENT_URL);
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Authorization", "Basic " + encodedCredentials);
        String postData = "grant_type=client_credentials&scope=api";
        con.setDoOutput(true);
        con.getOutputStream().write(postData.getBytes());
        Map<String, Object> credential = HttpRequestUtil.sendRequestAndGetBodyRes(con, HttpURLConnection.HTTP_OK);

        String accessToken = credential.getOrDefault("access_token", null).toString();
        long expiresIn = Double.valueOf(credential.getOrDefault("expires_in", 0).toString()).longValue();
        String scope = credential.getOrDefault("scope", null).toString();
        String tokenType = credential.getOrDefault("token_type", null).toString();
        this.credentials = new ClientCredentials(accessToken, expiresIn, scope, tokenType);
        return this.credentials;
    }

    public boolean isAuthenticated() {
        return this.credentials.isValidToken() && !this.credentials.isNotExpired();
    }
}
