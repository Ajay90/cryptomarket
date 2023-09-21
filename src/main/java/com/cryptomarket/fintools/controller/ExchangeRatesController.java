package com.cryptomarket.fintools.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptomarket.fintools.model.ExchangeRateResponse;
import com.cryptomarket.fintools.service.ExchangeRates;

/**
 * @author ADH
 *
 */
@RestController
@RequestMapping("/coinapi/v1")
public class ExchangeRatesController {

	private final ExchangeRates exchangeRates;

	public ExchangeRatesController(ExchangeRates exchangeRates) {
		super();
		this.exchangeRates = exchangeRates;
	}

	@GetMapping("/v1/exchangerate/{asset_id_base}/{asset_id_quote}/history")
	public ExchangeRateResponse[] exchangeRate(@RequestHeader String apiKey, @PathVariable String asset_id_base,
			@PathVariable String asset_id_quote, @RequestParam("period_id") String period_id,
			@RequestParam("limit") String limit) {
		return exchangeRates.exchangeRate(apiKey, asset_id_base, asset_id_quote, period_id, limit);
	}

}
