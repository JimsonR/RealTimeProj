package com.main.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Component
@Embeddable
public class TeamsPrimaryKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private int teamId;
	@ManyToOne
	@JoinColumn(name = "eventId",referencedColumnName = "eventId")
	Event eventid;
}
