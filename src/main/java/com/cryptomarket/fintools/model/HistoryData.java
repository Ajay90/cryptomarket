package com.cryptomarket.fintools.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class HistoryData {
	private String time_period_start; // Timesamp
	private String time_period_end; // Timesamp
	private String time_open; // Timesamp
	private String time_close; // Timesamp
	private double price_open;
	private double price_high;
	private double price_low;
	private double price_close;
}
