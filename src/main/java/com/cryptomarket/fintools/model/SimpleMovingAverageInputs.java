package com.cryptomarket.fintools.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SimpleMovingAverageInputs {
	private String period_id;
	private int limit;
	private String asset_id_base;
	private String asset_id_quote;
}
