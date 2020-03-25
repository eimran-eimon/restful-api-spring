package com.cokreates.rest.security;

import com.cokreates.rest.SpringApplicationContext;
import com.cokreates.rest.common.AppProperties;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000; // 10 days

	public static final String TOKEN_PREFIX = "X-Auth-Token ";

	public static final String JWT_HEADER_STRING = "Authorization";

	public static final String SIGN_UP_URL = "/users";

	public static String getSecretToken() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}

}
