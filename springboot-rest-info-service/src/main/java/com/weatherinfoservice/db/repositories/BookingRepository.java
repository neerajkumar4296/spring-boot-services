package com.weatherinfoservice.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherinfoservice.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, String> {

}
