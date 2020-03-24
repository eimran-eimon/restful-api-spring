package com.cokreates.rest.common;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final Integer ITERATION = 1000;

	public String generateUserId(int length) {
		return generateRandomString(length);
	}

	private String generateRandomString(int length) {
		StringBuilder stringBuilder = new StringBuilder(length);
		for (int i =0; i < length; i++){
			stringBuilder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(stringBuilder);

	}
}
