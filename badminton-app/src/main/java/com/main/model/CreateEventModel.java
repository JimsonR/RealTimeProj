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
	private String eventName;
	private int eventType;
	private int matchType;
	private int entryFee;
	private int maxEntries;
	private int discount;
//	private boolean allowBookings;
//	private int totalPools;
//	private int qualifiersPerPool;
//	public boolean getAllowBookings() {
//		return allowBookings;
//	}
}
