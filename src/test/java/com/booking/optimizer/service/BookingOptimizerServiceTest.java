package com.booking.optimizer.service;

import com.booking.optimizer.BookingOptimizerServiceApplication;
import com.booking.optimizer.model.request.BookingOptimizerRequest;
import com.booking.optimizer.model.response.BookingOptimizerResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@SpringBootConfiguration
@ContextConfiguration(classes = BookingOptimizerServiceApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BookingOptimizerServiceTest {

    @Autowired
    private BookingOptimizerService bookingOptimizer;

    private List<Integer> customerPayingList;

    @Before
    public void setUp() throws Exception {
        customerPayingList = Arrays.asList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209);
    }

    @Test
    public void shouldReturnThreeFreePremiumRoomAndThreeFreeEconomy() throws Exception {
        BookingOptimizerResponse expectedResponse = new BookingOptimizerResponse();
        expectedResponse.setEconomyRoomTotalRevenue(167);
        expectedResponse.setPremiumRoomTotalRevenue(738);
        expectedResponse.setUsedEconomyRoomCount(3);
        expectedResponse.setUsedPremiumRoomCount(3);

        Assert.assertEquals(bookingOptimizer.optimize(BookingOptimizerRequest.builder().freeEconomyRoomCount(3).
                freePremiumRoomCount(3).customerPaymentList(customerPayingList).build()), expectedResponse);
    }


}