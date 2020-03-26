package com.cokreates.rest.common;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {

	private final Environment environment;

	public AppProperties(Environment environment) {
		this.environment = environment;
	}

	public String getTokenSecret() {
		return environment.getProperty("tokenSecret");
	}

}
