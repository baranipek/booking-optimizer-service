package com.booking.optimizer.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * it holds the response data to api after calculation.
 */
@Data
@ApiModel
public class BookingOptimizerResponse implements Serializable {

    private static final long serialVersionUID = -58487769753249508L;

    @ApiModelProperty(position = 1, required = true, value = "used Premium room count")
    private int usedPremiumRoomCount;

    @ApiModelProperty(position = 2, required = true, value = "used economy room count")
    private int usedEconomyRoomCount;

    @ApiModelProperty(position = 3, required = true, value = "total revenue from economy rooms")
    private int economyRoomTotalRevenue;

    @ApiModelProperty(position = 4, required = true, value = "total revenue from premium rooms")
    private int premiumRoomTotalRevenue;

}
