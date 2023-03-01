package io.github.galaxyed;

import java.time.LocalDateTime;

public class ClientCredentials {
    public String accessToken;
    private LocalDateTime expired_at;
    public static final int GAP_TIME = 2;
    private String scope;
    private String tokenType;

    ClientCredentials(String accessToken, long expiresIn ,String scope, String tokenType) {
        this.accessToken = accessToken;
        this.expired_at = expiresIn != 0 ? LocalDateTime.now().plusSeconds(expiresIn - GAP_TIME) : null;
        this.scope = scope;
        this.tokenType = tokenType;
    }

    public static ClientCredentials getNullCredential() {
        return new ClientCredentials(null, 0, null, null);
    }

    boolean isValidToken() {
        return this.accessToken != null;
    }

    boolean isNotExpired() {
        return this.expired_at != null && this.expired_at.isAfter(LocalDateTime.now());
    }

}
