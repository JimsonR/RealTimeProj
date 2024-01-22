package com.main.entity;

import org.springframework.data.annotation.Reference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int organizationId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId",referencedColumnName = "userId")
	private Users userId;
	private String organizationName;
	private String emailId;
	private String mobileNumber;
}
