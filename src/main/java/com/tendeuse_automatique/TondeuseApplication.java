package com.tendeuse_automatique;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@EntityScan("com.tendeuse_automatique")
@ComponentScan(basePackages = {"com.tendeuse_automatique"})
@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class TondeuseApplication implements CommandLineRunner, ExitCodeGenerator {

	private final JobLauncher jobLauncher;
	private final Job tendeusejob;
	private final ExitCodeMapper exitCodeMapper;
	private int returnCode;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(TondeuseApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		System.exit(SpringApplication.exit(ctx));
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			long start = System.currentTimeMillis();
			JobExecution jobExecution = jobLauncher.run(tendeusejob, new JobParametersBuilder()
					.addLong("time", start)
					.toJobParameters());
			returnCode = exitCodeMapper.intValue(jobExecution.getExitStatus().getExitCode());
		};

	}

	@Override
	public void run(String... args) {
		log.info("L'ecriture du fichier s'est terminée à " + LocalDateTime.now());
	}

	@Override
	public int getExitCode() {
		return returnCode;
	}

	@Component
	public static class CommandLineAppStartupRunner implements CommandLineRunner {

		@Value("${input.file.path:}")
		private String filePath;

		@Override
		public void run(String... args) {
			if (filePath == null || filePath.isEmpty()) {
				throw new IllegalArgumentException("File path argument is required");
			}
			log.info("File path: {}" , filePath);
		}
	}

}
