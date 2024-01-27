package com.main.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class TeamsNEventId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name="teamId",referencedColumnName = "teamId")
	Teams team_id;
	
	@ManyToOne
	@JoinColumn(name="eventId",referencedColumnName = "eventId")
	Event eventId;
}
