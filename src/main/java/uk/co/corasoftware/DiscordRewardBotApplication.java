package uk.co.corasoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class DiscordRewardBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscordRewardBotApplication.class, args);
	}

}
