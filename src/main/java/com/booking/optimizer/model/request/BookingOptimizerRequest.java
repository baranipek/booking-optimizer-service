package com.booking.optimizer.model.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * it holds the request data comes from api.
 */
@Data
@Builder
public class BookingOptimizerRequest implements Serializable {

    private static final long serialVersionUID = -7390018313839302362L;

    private int freeEconomyRoomCount;

    private int freePremiumRoomCount;

    private List<Integer> customerPaymentList;
}
