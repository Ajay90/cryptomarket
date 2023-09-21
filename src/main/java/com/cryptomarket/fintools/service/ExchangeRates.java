package com.cryptomarket.fintools.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cryptomarket.fintools.configuration.CoinAPIConfiguration;
import com.cryptomarket.fintools.model.ExchangeRateResponse;

/**
 * @author ADH
 *
 */
@Service
public class ExchangeRates {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRates.class);
	private final RestTemplate restTemplate = new RestTemplate();
	private final CoinAPIConfiguration coinAPIConfiguration;
	private final HeaderHttpEntityServiceCoinAPI headerHttpEntityServiceCoinAPI;

	public ExchangeRates(CoinAPIConfiguration coinAPIConfiguration,
			HeaderHttpEntityServiceCoinAPI headerHttpEntityServiceCoinAPI) {
		super();
		this.coinAPIConfiguration = coinAPIConfiguration;
		this.headerHttpEntityServiceCoinAPI = headerHttpEntityServiceCoinAPI;
	}

	public ExchangeRateResponse[] exchangeRate(String apiKey, String asset_id_base, String asset_id_quote,
			String periodID, String limit) {
		ResponseEntity<ExchangeRateResponse[]> responseEntity = null;
		try {

			MultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();
			linkedMultiValueMap.set("period_id", periodID);
			linkedMultiValueMap.set("limit", limit);

			URI uri = null;
			if (asset_id_base != null && !asset_id_base.isBlank() && asset_id_quote != null && !asset_id_quote.isBlank()
					&& periodID != null && !periodID.isBlank()) {
				uri = UriComponentsBuilder.fromUriString(coinAPIConfiguration.getUrl())
						.path(coinAPIConfiguration.getExchange_rate()).queryParams(linkedMultiValueMap)
						.buildAndExpand(asset_id_base, asset_id_quote).toUri();
			}
			HttpEntity<?> httpEntity = headerHttpEntityServiceCoinAPI.createHttpEntity(apiKey);
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, ExchangeRateResponse[].class);

		} catch (HttpClientErrorException httpClientErrorException) {
			LOGGER.error(httpClientErrorException.getResponseBodyAsString());
			httpClientErrorException.printStackTrace();
		}
		return responseEntity.getBody();
	}

}
