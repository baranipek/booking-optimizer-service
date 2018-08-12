package com.booking.optimizer.api;

import com.booking.optimizer.model.request.BookingOptimizerRequest;
import com.booking.optimizer.model.response.BookingOptimizerResponse;
import com.booking.optimizer.service.BookingOptimizerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * the service class holds all endpoints
 */
@RestController
@RequestMapping(value = "/api/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
public class BookingOptimizerApi {

    @Autowired
    private BookingOptimizerService bookingOptimizerService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Optimize booking request")
    public ResponseEntity<BookingOptimizerResponse> optimizeBooking(BookingOptimizerRequest request) {
        return new ResponseEntity<>(this.bookingOptimizerService.optimize(request), HttpStatus.OK);
    }


}
