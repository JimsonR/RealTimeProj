package com.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventModel {
	private int category;
	private int matchType;
	private int entryFee;
	private int maxEntries;
	private boolean allowBookings;
	public boolean getAllowBookings() {
		return allowBookings;
	}
}
