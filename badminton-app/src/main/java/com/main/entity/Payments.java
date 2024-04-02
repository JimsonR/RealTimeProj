package com.main.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Payments {
    @Id
            @GeneratedValue(strategy=GenerationType.AUTO)
    int paymentId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamId", referencedColumnName = "teamId")
    Teams teamId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="eventId",referencedColumnName = "eventId")
    Event eventId;
    
    String uniqueId;
    
    Date date;
    int amount;
    boolean paymentMode;
    int paymentStatus;

}
