package com.sprint2.webapi;

import com.sprint2.webapi.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class WebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebapiApplication.class, args);
	}

}
