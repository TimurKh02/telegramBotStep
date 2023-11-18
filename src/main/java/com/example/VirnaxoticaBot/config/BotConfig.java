package com.example.VirnaxoticaBot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class BotConfig {

	@Value("${bot.name}")
	String botName;

	@Value("${bot.key}")
	String token;

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BotConfig(String botName, String token) {
		super();
		this.botName = botName;
		this.token = token;
	}

	public BotConfig() {
		super();
	}

}
