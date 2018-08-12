package com.booking.optimizer.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * it holds the request data comes from api.
 */
@Data
@Builder
@ApiModel
public class BookingOptimizerRequest implements Serializable {

    private static final long serialVersionUID = -7390018313839302362L;

    @ApiModelProperty(position = 1, required = true, value = "available free rooms")
    private int freeEconomyRoomCount;

    @ApiModelProperty(position = 2, required = true, value = "available premium rooms")
    private int freePremiumRoomCount;

    @ApiModelProperty(position = 3, required = true, value = "customer paying amount")
    private List<Integer> customerPaymentList;


}
