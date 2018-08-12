package com.booking.optimizer.service;


import com.booking.optimizer.mapper.DtoMapper;
import com.booking.optimizer.model.entity.Customer;
import com.booking.optimizer.model.entity.CustomerSegmentLimit;
import com.booking.optimizer.model.request.BookingOptimizerRequest;
import com.booking.optimizer.model.response.BookingOptimizerResponse;
import com.booking.optimizer.repository.CustomerRepository;
import com.booking.optimizer.repository.CustomerSegmentLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * the service class holds all business application in the project
 */
@Service
public class BookingOptimizerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerSegmentLimitRepository customerSegmentLimitRepository;

    @Autowired
    private DtoMapper mapper;

    /**
     * run algorithm and execute the segmentation rules in the request
     *
     * @param request holds the request to be calculated
     * @return BookingOptimizerResponse response after calculation
     */

    public BookingOptimizerResponse optimize(BookingOptimizerRequest request) {
        Collections.sort(request.getCustomerPaymentList(), Collections.reverseOrder());

        this.initCustomerRequest(request);
        BookingOptimizerResponse response = new BookingOptimizerResponse();
        List<Customer> allCustomers = (List<Customer>) customerRepository.findAll();

        List<Customer> economyPaymentList = new ArrayList<>();
        List<Customer> premiumPaymentList = new ArrayList<>();

        return response;
    }

    /**
     * save the request for the initial request.
     *
     * @param request holds the request to check is this first request or not
     */
    private void initCustomerRequest(BookingOptimizerRequest request) {
        if (customerRepository.count() == 0) {
            CustomerSegmentLimit limit = new CustomerSegmentLimit();
            limit.setCustomerSegmentationLimit(100);

            customerRepository.saveAll(mapper.map(request));
            customerSegmentLimitRepository.save(limit);
        }
    }


}
