package com.webacademy.padaria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.webacademy.padaria.config.StorageProperties;
import com.webacademy.padaria.storage.FileSystemStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PadariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PadariaApplication.class, args);
	}

	@Bean
	CommandLineRunner init(FileSystemStorageService storageService){
		return (args) -> {
			storageService.init();
		};
	}

}
