package com.cryptomarket.fintools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan({ "com.cryptomarket.fintools.configuration" })
public class CryptoMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoMarketApplication.class, args);
	}

}
