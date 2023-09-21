package com.cryptomarket.fintools.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

/**
 * @author ADH
 *
 */
@Service
public class HeaderHttpEntityServiceCoinAPI {

	public HttpEntity<?> createHttpEntity(String apiKey) {
		HttpHeaders httpHeaders = createHttpHeaders(apiKey);
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		return httpEntity;

	}

	private HttpHeaders createHttpHeaders(String apiKey) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("X-CoinAPI-Key", apiKey);
		httpHeaders.set("Accept", "*/*");
		return httpHeaders;

	}
}
