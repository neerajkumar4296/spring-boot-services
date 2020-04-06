package com.weatherinfoservice.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.weatherinfoservice.model.Booking;

@Repository
public class ServicesDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ServicesDao.class);
	
	private static final String INSERT_BOOKING_QUERY= "INSERT INTO BOOKING "
			+ "(BOOKINGID, BOOKINGTIME, FIRSTNAME ,LASTNAME ,MOBILENO ,NOOFTICKET, SOURCE, DESTINATION) "
			+ "VALUES(?,?,?,?,?,?,?,?)";

	@Autowired
	@Qualifier("userDBTemplate")
	JdbcTemplate userDBTemplate;
	

	public void saveBooking(Booking	booking) {
		logger.info("Saving Booking... " +booking.getBookingId());
		userDBTemplate.update(INSERT_BOOKING_QUERY, new Object[] { booking.getBookingId(),booking.getBookingTime(),
					booking.getPassenger().getFirstName(), booking.getPassenger().getLastName().get(), booking.getPassenger().getMobileNo(), 
				    booking.getPassenger().getNoOfTicket(), booking.getPassenger().getSource(), booking.getPassenger().getDestination()});
		
	}
}
