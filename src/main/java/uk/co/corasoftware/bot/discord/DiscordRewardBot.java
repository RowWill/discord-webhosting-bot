package uk.co.corasoftware.bot.discord;

import org.springframework.beans.factory.annotation.Value;

public class DiscordRewardBot {

	@Value("${bot.discord.token}")
	private String discord_token;

}
