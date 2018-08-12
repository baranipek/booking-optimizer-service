package com.booking.optimizer.service;


import com.booking.optimizer.enums.CustomerSegment;
import com.booking.optimizer.mapper.DtoMapper;
import com.booking.optimizer.model.entity.Customer;
import com.booking.optimizer.model.entity.CustomerSegmentLimit;
import com.booking.optimizer.model.request.BookingOptimizerRequest;
import com.booking.optimizer.model.response.BookingOptimizerResponse;
import com.booking.optimizer.repository.CustomerRepository;
import com.booking.optimizer.repository.CustomerSegmentLimitRepository;
import com.booking.optimizer.utility.ListOperationsUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ListOperationsUtility utility;

    /**
     * run algorithm and execute the segmentation rules in the request
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

        allCustomers.forEach(utility.splitBy(customerPaying ->
                        customerPaying.getCustomerPayment() < customerSegmentLimitRepository.findById(1L).
                                get().getCustomerSegmentationLimit(),
                economyPaymentList::add, premiumPaymentList::add));

        this.bookEconomyRooms(economyPaymentList, response, request.getFreeEconomyRoomCount());
        this.bookPremiumRooms(premiumPaymentList, response, request.getFreePremiumRoomCount());

        this.upgradeEconomyCustomer(economyPaymentList.get(0));

        return response;
    }


    /**
     * book the economy first
     *
     * @param economyPaymentList   holds customer payments amount for economy
     * @param response             holds the response to be set
     * @param freeEconomyRoomCount free economy rooms at hotel
     */
    private void bookEconomyRooms(List<Customer> economyPaymentList, BookingOptimizerResponse response,
                                  int freeEconomyRoomCount) {
        List<Customer> economyRoomsUsedList;
        if (freeEconomyRoomCount <= economyPaymentList.size()) {
            economyRoomsUsedList = economyPaymentList.stream().limit(freeEconomyRoomCount).collect(Collectors.toList());
            response.setEconomyRoomTotalRevenue(economyRoomsUsedList.stream().mapToInt(Customer::getCustomerPayment).sum());
            response.setUsedEconomyRoomCount(economyRoomsUsedList.size());
        } else {
            response.setEconomyRoomTotalRevenue(economyPaymentList.stream().mapToInt(Customer::getCustomerPayment).sum());
            response.setUsedEconomyRoomCount(economyPaymentList.size());
        }

    }

    /**
     * book the premium then
     *
     * @param premiumPaymentList   holds customer payments amount for premium
     * @param response             holds the response to be set
     * @param freePremiumRoomCount free premium rooms at hotel
     */
    private void bookPremiumRooms(List<Customer> premiumPaymentList, BookingOptimizerResponse response,
                                  int freePremiumRoomCount) {

        List<Customer> premiumRoomsUsedList;
        if (freePremiumRoomCount <= premiumPaymentList.size()) {
            premiumRoomsUsedList = premiumPaymentList.stream().limit(freePremiumRoomCount).collect(Collectors.toList());
            response.setPremiumRoomTotalRevenue(premiumRoomsUsedList.stream().mapToInt(Customer::getCustomerPayment).sum());
            response.setUsedPremiumRoomCount(premiumRoomsUsedList.size());
        } else {
            response.setPremiumRoomTotalRevenue(premiumPaymentList.stream().mapToInt(Customer::getCustomerPayment).sum());
            response.setUsedPremiumRoomCount(premiumPaymentList.size());
        }
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


    /**
     * upgrade the economy customer
     *
     * @param customer Customer entity
     */
    private void upgradeEconomyCustomer(Customer customer) {
        customer.setSegment(CustomerSegment.PREMIUM);
        customerRepository.save(customer);
    }

}
