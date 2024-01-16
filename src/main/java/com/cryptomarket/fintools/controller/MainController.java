package com.cryptomarket.fintools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cryptomarket.fintools.configuration.CoinAPIConfiguration;
import com.cryptomarket.fintools.model.SimpleMovingAverageInputs;
import com.cryptomarket.fintools.model.SimpleMovingAverageResponse;
import com.cryptomarket.fintools.service.SimpleMovingAverage;

@Controller
public class MainController {

	private final SimpleMovingAverage simpleMovingAverage;
	private final CoinAPIConfiguration coinAPIConfiguration;

	public MainController(SimpleMovingAverage simpleMovingAverage, CoinAPIConfiguration coinAPIConfiguration) {
		super();
		this.simpleMovingAverage = simpleMovingAverage;
		this.coinAPIConfiguration = coinAPIConfiguration;
	}

	@GetMapping("/sma")
	public String showForm(Model model) {
		SimpleMovingAverageInputs simpleMovingAverageInputs = new SimpleMovingAverageInputs();
		model.addAttribute("simpleMovingAverageInputs", simpleMovingAverageInputs);

		return "sma_input";
	}

	@PostMapping("/smaresult")
	public String submitForm(
			@ModelAttribute("simpleMovingAverageInputs") SimpleMovingAverageInputs simpleMovingAverageInputs,
			Model model) {
		SimpleMovingAverageResponse simpleMovingAverageResponse = new SimpleMovingAverageResponse();
		simpleMovingAverageResponse.setResult(simpleMovingAverage.calculdateSimpleMovingAverageByOHLCV(
				coinAPIConfiguration.getApiKey(), simpleMovingAverageInputs.getAsset_id_base(),
				simpleMovingAverageInputs.getAsset_id_quote(), simpleMovingAverageInputs.getPeriod_id(),
				String.valueOf(simpleMovingAverageInputs.getLimit()), simpleMovingAverageInputs.getSymbol_id()));

		model.addAttribute("simpleMovingAverageResponse", simpleMovingAverageResponse);
		System.out.println(simpleMovingAverageResponse);
		return "calculate_sma";
	}

	@PostMapping("/smaresult2")
	public String submitForm2(
			@ModelAttribute("simpleMovingAverageInputs") SimpleMovingAverageInputs simpleMovingAverageInputs,
			Model model) {
		System.out.println(simpleMovingAverageInputs);

		SimpleMovingAverageResponse simpleMovingAverageResponse = new SimpleMovingAverageResponse();
		simpleMovingAverageResponse.setResult(simpleMovingAverage.calculdateSimpleMovingAverageByExchangeRate(
				coinAPIConfiguration.getApiKey(), simpleMovingAverageInputs.getAsset_id_base(),
				simpleMovingAverageInputs.getAsset_id_quote(), simpleMovingAverageInputs.getPeriod_id(),
				String.valueOf(simpleMovingAverageInputs.getLimit())));

		model.addAttribute("simpleMovingAverageResponse", simpleMovingAverageResponse);
		System.out.println(simpleMovingAverageResponse);
		return "calculate_sma2";
	}
}
