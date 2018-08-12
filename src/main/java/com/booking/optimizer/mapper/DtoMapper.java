package com.booking.optimizer.mapper;


import com.booking.optimizer.enums.CustomerSegment;
import com.booking.optimizer.model.entity.Customer;
import com.booking.optimizer.model.request.BookingOptimizerRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * the mapper class maps the request into entity
 */
@Component
public class DtoMapper {
    public Iterable<Customer> map(BookingOptimizerRequest request) {
        return request.getCustomerPaymentList().stream().
                map(this::map).collect(Collectors.toList());
    }

    private Customer map(Integer customerPayment) {
        Customer customer = new Customer();
        customer.setCustomerPayment(customerPayment);
        customer.setSegment(customerPayment > 100 ? CustomerSegment.PREMIUM : CustomerSegment.ECONOMY);
        return customer;
    }
}
