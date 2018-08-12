package com.booking.optimizer.model.entity;

import com.booking.optimizer.enums.CustomerSegment;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int customerPayment;

    private CustomerSegment segment;

}
