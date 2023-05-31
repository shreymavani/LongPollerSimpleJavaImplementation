package com.cloudera;

import com.cloudera.apiHandler.LongPollingConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(LongPollingConfig.class)
public class LongPollingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LongPollingApplication.class, args);
	}

}
