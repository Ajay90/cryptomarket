package com.cryptomarket.fintools.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cryptomarket.fintools.model.ExchangeRateResponse;

/**
 * @author ADH
 *
 */
@Service
public class SimpleMovingAverage {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMovingAverage.class);

	private final ExchangeRates exchangeRates;

	public SimpleMovingAverage(ExchangeRates exchangeRates) {
		super();
		this.exchangeRates = exchangeRates;
	}

	public double calculdateSimpleMovingAverage(String apiKey, String asset_id_base, String asset_id_quote,
			String periodID, String limit) {
		ExchangeRateResponse[] exchangeRateResponse = exchangeRates.exchangeRate(apiKey, asset_id_base, asset_id_quote,
				periodID, limit);

		double sumOfRateClose = 0;
		int exchangeRateResponseLength = exchangeRateResponse.length;
		for (int i = 0; i < exchangeRateResponseLength; i++) {
			LOGGER.info("Rate close " + i + ": " + exchangeRateResponse[i].getRate_close());
			sumOfRateClose += exchangeRateResponse[i].getRate_close();
		}
		LOGGER.info("Sum: " + sumOfRateClose);
		double simpleMovingAverage = sumOfRateClose / exchangeRateResponseLength;
		LOGGER.info("Average: " + simpleMovingAverage);
		return simpleMovingAverage;
	}
}
