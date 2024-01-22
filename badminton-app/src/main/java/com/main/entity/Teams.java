package com.main.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Teams {
	
	
	@EmbeddedId
	private TeamsPrimaryKey primaryKey;
	private String player1;
	private String player2;
	private String emailId;
	private String phoneNumber;
	

}
