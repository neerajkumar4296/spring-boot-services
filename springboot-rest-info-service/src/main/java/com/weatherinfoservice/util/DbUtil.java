package com.weatherinfoservice.util;

import java.time.LocalDate;
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
		Employee employee= new 
				Employee(986845L, "Neeraj", "Kumar", LocalDate.of(1995, 12, 31), 50000L, Qualification.GRADUATE, 
				new Address("B155/303", "", "", "700135", "West Bengal", "India", AddressType.TEMPORARY));

//				SessionFactory sessionFactory= new Configuration().configure().buildSessionFactory();
//				Session session = sessionFactory.openSession();
//				session.beginTransaction();
//				session.save(employee);
//				session.getTransaction().commit();
//				
//				session.close();
		
		empRepository.save(employee);
		
	}

}
