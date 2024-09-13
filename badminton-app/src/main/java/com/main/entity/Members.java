package com.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Members {

    private int memberId;
    private String email;
    private String name;
    private Long phoneNumber;
    private String password;
    @JoinColumn(name = "organization")
    @ManyToOne
    private Organization organization;




}
