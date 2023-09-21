package com.cryptomarket.fintools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class ExchangeRateResponse {
	private String time_period_start; // Timesamp
	private String time_period_end; // Timesamp
	private String time_open; // Timesamp
	private String time_close; // Timesamp
	private double rate_open;
	private double rate_high;
	private double rate_low;
	private double rate_close;
}
