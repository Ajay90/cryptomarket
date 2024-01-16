package com.cryptomarket.fintools.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptomarket.fintools.service.SimpleMovingAverage;

/**
 * @author ADH
 *
 */
@RestController
@RequestMapping("/coinapi/v1")
public class SimpleMovingAverageController {

	private final SimpleMovingAverage simpleMovingAverage;

	public SimpleMovingAverageController(SimpleMovingAverage simpleMovingAverage) {
		super();
		this.simpleMovingAverage = simpleMovingAverage;
	}

	@GetMapping("/simpleMovingAverage")
	public double simpleMovingAverage(@RequestHeader String apiKey, @RequestParam("asset_id_base") String asset_id_base,
			@RequestParam String asset_id_quote, @RequestParam("period_id") String period_id,
			@RequestParam("limit") String limit, @RequestParam("symbol_id") String symbol_id) {
		return simpleMovingAverage.calculdateSimpleMovingAverageByOHLCV(apiKey, asset_id_base, asset_id_quote,
				period_id, limit, symbol_id);
	}

}
