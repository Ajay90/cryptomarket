package com.cryptomarket.fintools.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ADH
 *
 */
@ConfigurationProperties(prefix = "coinapi")
@Getter
@Setter
public class CoinAPIConfiguration {
	private String url;
	private String apiKey;
	private String list_all_periods;
	private String latest_data;
	private String historical_data;
	private String symbols;
	private String asset_icons;
	private String assets;
	private String exchange_rate;
}
