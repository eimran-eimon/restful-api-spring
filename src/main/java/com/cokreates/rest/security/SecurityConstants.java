package com.cokreates.rest.security;

public class SecurityConstants {

	public static final String TOKEN_SECRET = "SecretKeyToGenJWTs123456789012345678901234567890:SecretKeyToGenJWTs123456789012345678901234567890:SecretKeyToGenJWTs123456789012345678901234567890";

	public static final long EXPIRATION_TIME = 864000000; // 10 days

	public static final String TOKEN_PREFIX = "X-Auth-Token ";

	public static final String JWT_HEADER_STRING = "Authorization";

	public static final String SIGN_UP_URL = "/users";

}
