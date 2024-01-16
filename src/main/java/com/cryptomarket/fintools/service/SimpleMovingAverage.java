package com.cryptomarket.fintools.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cryptomarket.fintools.model.HistoryData;

/**
 * @author ADH
 *
 */
@Service
public class SimpleMovingAverage {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMovingAverage.class);

	private final ExchangeRates exchangeRates;
	private final OHLCVHistoricData ohlcvHistoricData;

	public SimpleMovingAverage(ExchangeRates exchangeRates, OHLCVHistoricData ohlcvHistoricData) {
		super();
		this.exchangeRates = exchangeRates;
		this.ohlcvHistoricData = ohlcvHistoricData;
	}

	public double calculdateSimpleMovingAverageByExchangeRate(String apiKey, String asset_id_base,
			String asset_id_quote, String periodID, String limit) {
		HistoryData[] exchangeRateResponse = exchangeRates.exchangeRate(apiKey, asset_id_base, asset_id_quote, periodID,
				limit);

		double sumOfRateClose = 0;
		int exchangeRateResponseLength = exchangeRateResponse.length;
		for (int i = 0; i < exchangeRateResponseLength; i++) {
			LOGGER.info("Rate close " + i + ": " + exchangeRateResponse[i].getPrice_close());
			sumOfRateClose += exchangeRateResponse[i].getPrice_close();
		}
		LOGGER.info("Sum: " + sumOfRateClose);
		double simpleMovingAverage = sumOfRateClose / exchangeRateResponseLength;
		LOGGER.info("Average: " + simpleMovingAverage);
		return simpleMovingAverage;
	}

	public double calculdateSimpleMovingAverageByOHLCV(String apiKey, String asset_id_base, String asset_id_quote,
			String periodID, String limit, String symbolID) {
		HistoryData[] historyData = ohlcvHistoricData.symbolIdHistoryData(apiKey, asset_id_base, asset_id_quote,
				periodID, limit, symbolID);

		double sumOfPriceClose = 0;
		int historyDataResponseLength = historyData.length;
		for (int i = 0; i < historyDataResponseLength; i++) {
			LOGGER.info("Price close " + i + ": " + historyData[i].getPrice_close());
			sumOfPriceClose += historyData[i].getPrice_close();
		}
		LOGGER.info("Sum: " + sumOfPriceClose);

		double simpleMovingAverage = sumOfPriceClose / historyDataResponseLength;
		LOGGER.info("Average: " + simpleMovingAverage);
		return simpleMovingAverage;
	}
}
