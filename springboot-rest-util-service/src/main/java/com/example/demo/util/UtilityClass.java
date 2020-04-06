/**
 * 
 */
package com.example.demo.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author neerkuma
 *
 */
public class UtilityClass {
	
	private static final Logger logger = LoggerFactory.getLogger(UtilityClass.class);
	

	
	public  static Map<String, String> getSystemProperties(Properties props){
		logger.debug("In getSystemProperties:: ");
		Map<String, String> propMap= new HashMap<>();
    	Enumeration<Object> keys = props.keys();
    	while (keys.hasMoreElements()) {
    	    String key = (String)keys.nextElement();
    	    String value = (String)props.get(key);
    	    propMap.put(key, value);
    	    logger.info(key.toUpperCase() + ":::-> " + value);
    	}
		
		return propMap;
		
		
	}
	
	public static Map<String, String> getLocalisedMapValues(){
		Map<String, String> localisedMap= new HashMap<>();
		localisedMap.put("hobby", "loisir");
		localisedMap.put("name", "Nom");
		return localisedMap;
		
		
	}
	
	

	
	
	

}
