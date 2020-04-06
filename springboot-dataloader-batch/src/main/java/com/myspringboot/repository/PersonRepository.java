package com.myspringboot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myspringboot.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	@Query(value="SELECT * FROM PERSONS  WHERE FIRST_NAME=?1 and LAST_NAME=?2", nativeQuery = true)
	public Person findByFirstAndLastName(String firstName, String lastName);
}
