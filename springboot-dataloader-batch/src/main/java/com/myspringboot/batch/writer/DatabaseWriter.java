package com.myspringboot.batch.writer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.myspringboot.model.Person;
import com.myspringboot.provider.ServiceProvider;
import com.myspringboot.repository.PersonRepository;

@Component
public class DatabaseWriter implements ItemWriter<Person> {

	private static final Logger log = LoggerFactory.getLogger(DatabaseWriter.class);
	
    @Autowired
    private ServiceProvider provider;

    @Value(value ="${app.defaultpasswordlength}")
    private int defaultPassLength; 

	@Autowired
	private PersonRepository personRepository;

	@Override
	public void write(List<? extends Person> persons) throws Exception {
		// personRepository.save(items);
    persons.stream().forEach(person ->  {
    	
    if (!ifPersonExists(person)) {
       person.setPassword(provider.generateRandomPass(defaultPassLength));
       person.setUpdateDate(new Date());
	   personRepository.save(person);
	   log.info("saving to Database: " + person);
	}} );
		
		/*
		 * for (Person person : persons) { // personRepository.save(person); if
		 * (!ifPersonExists(person)) {
		 * person.setPassword(provider.generateRandomPass(defaultPassLength));
		 * person.setUpdateDate(new Date()); personRepository.save(person);
		 * log.info("saving to Database: " + person); }
		 * 
		 * //log.info("Person already exists:: " + person);
		 * 
		 * }
		 */

	}

	public boolean ifPersonExists(Person person) {

		if (personRepository.findByFirstAndLastName
		   (person.getFirstName(), person.getLastName())
			== null)
		{
			log.info("Person does not exist :: " + person);
			return false;
		}
		log.info("Person already exists in the DB:: " + person.getFirstName()+ " "+person.getLastName());
		return true;

	}

}
