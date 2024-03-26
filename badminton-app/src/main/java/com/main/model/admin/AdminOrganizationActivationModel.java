package com.main.model.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminOrganizationActivationModel {
	private int featureId;
	private int organizationId;
	private boolean value;
	
	public boolean getValue() {
		return value;
	}
	
}
