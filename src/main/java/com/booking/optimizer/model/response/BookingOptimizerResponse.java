package com.booking.optimizer.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * it holds the response data to api after calculation.
 */
@Data
public class BookingOptimizerResponse implements Serializable {

    private static final long serialVersionUID = -58487769753249508L;

    private int usedPremiumRoomCount;

    private int usedEconomyRoomCount;

    private int economyRoomTotalRevenue;

    private int premiumRoomTotalRevenue;

}