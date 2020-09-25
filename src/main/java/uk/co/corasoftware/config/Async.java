package uk.co.corasoftware.config;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
@EnableScheduling
public class Async {

	private static final Logger LOG = LoggerFactory.getLogger(Async.class);

	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {
		LOG.debug("Creating Async Task Executor");

		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("BotThread-");
		executor.initialize();

		return executor;
	}
}
