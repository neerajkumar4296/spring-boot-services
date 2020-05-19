package com.weatherinfoservice.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.weatherinfoservice.db.repositories.EmployeeRepository;
import com.weatherinfoservice.model.Address;
import com.weatherinfoservice.model.AddressType;
import com.weatherinfoservice.model.Employee;
import com.weatherinfoservice.model.Qualification;

@Component
public class DbUtil {

	@Autowired
	EmployeeRepository empRepository;
	
		public void saveEmployeeDetails() {
		
			Address addresstemp= new Address("B155/303", "Sukhobrishti, Shapoorji", "Kolkata", "West Bengal", "India", 700135, AddressType.TEMPORARY);
			Address addressperm= new Address("141", "Station Road,", "Khagaria", "Bihar", "India", 851204, AddressType.HOME);
		    Collection<Address> addressesNeeraj = new ArrayList<>();
		    addressesNeeraj.add(addresstemp);
		    addressesNeeraj.add(addressperm);
			
		    Address addresstempO= new Address("B155/303", "Sukhobrishti, Shapoorji", "Kolkata", "West Bengal", "India", 700135, AddressType.TEMPORARY);
			Address addresspermO= new Address("129", "Thana Chowk", "Samastipur", "Bihar", "India", 851248, AddressType.HOME);
		    Collection<Address> addressesOther= new ArrayList<>();
			addressesOther.add(addresstempO);
			addressesOther.add(addresspermO);
			
		Employee empNeeraj= new 
				Employee(986845L, "Neeraj", "Kumar", LocalDate.of(1995, 12, 31), 50000L, Qualification.GRADUATE, addressesNeeraj);
		
		Employee empOther= new 
				Employee(1073214L, "Aditya", "Gupta", LocalDate.of(1992, 03, 24), 60000L, Qualification.GRADUATE, addressesOther);
		
		

//				SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
//				Session session = sessionFactory.openSession();
//				session.beginTransaction();
//				session.save(empNeeraj);
//				session.getTransaction().commit();
//			    session.close();
		
				empRepository.saveAll(Arrays.asList(empNeeraj, empOther));
		
		
		
	}

}
