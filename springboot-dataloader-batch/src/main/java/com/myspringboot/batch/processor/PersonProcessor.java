package com.myspringboot.batch.processor;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.myspringboot.model.Person;

@Component
public class PersonProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonProcessor.class);
    

    
    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();
                              
        final Person transformedPerson = new Person(firstName, lastName);

        log.debug("Transforming (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
