package com.galaxyed;

import io.github.cdimascio.dotenv.Dotenv;

public class Constants {
    public static final Dotenv dotenv = Dotenv.configure().load();
    public static final String USER_INFO_URL = dotenv.get("USER_INFO_URL");
    public static final String MERCHANT_IDS_URL = dotenv.get("MERCHANT_IDS_URL");
    public static final String CREDENTIAL_CLIENT_URL = dotenv.get("CREDENTIAL_CLIENT_URL");
}
