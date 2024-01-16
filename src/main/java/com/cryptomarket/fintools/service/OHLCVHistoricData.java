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
import com.cryptomarket.fintools.model.HistoryData;

/**
 * @author ADH
 *
 */
@Service
public class OHLCVHistoricData {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRates.class);
	private final RestTemplate restTemplate = new RestTemplate();
	private final CoinAPIConfiguration coinAPIConfiguration;
	private final HeaderHttpEntityServiceCoinAPI headerHttpEntityServiceCoinAPI;

	public OHLCVHistoricData(CoinAPIConfiguration coinAPIConfiguration,
			HeaderHttpEntityServiceCoinAPI headerHttpEntityServiceCoinAPI) {
		super();
		this.coinAPIConfiguration = coinAPIConfiguration;
		this.headerHttpEntityServiceCoinAPI = headerHttpEntityServiceCoinAPI;
	}

	public HistoryData[] symbolIdHistoryData(String apiKey, String asset_id_base, String asset_id_quote,
			String periodID, String limit, String symbolID) {
		ResponseEntity<HistoryData[]> responseEntity = null;
		try {

			MultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();
			linkedMultiValueMap.set("period_id", periodID);
			linkedMultiValueMap.set("limit", limit);

			URI uri = null;
			if (asset_id_base != null && !asset_id_base.isBlank() && asset_id_quote != null && !asset_id_quote.isBlank()
					&& periodID != null && !periodID.isBlank()) {
				uri = UriComponentsBuilder.fromUriString(coinAPIConfiguration.getUrl())
						.path(coinAPIConfiguration.getHistorical_data()).queryParams(linkedMultiValueMap)
						.buildAndExpand(symbolID).toUri();
			}
			HttpEntity<?> httpEntity = headerHttpEntityServiceCoinAPI.createHttpEntity(apiKey);
			responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, HistoryData[].class);

		} catch (HttpClientErrorException httpClientErrorException) {
			LOGGER.error(httpClientErrorException.getResponseBodyAsString());
			httpClientErrorException.printStackTrace();
		}
		return responseEntity.getBody();
	}

}
