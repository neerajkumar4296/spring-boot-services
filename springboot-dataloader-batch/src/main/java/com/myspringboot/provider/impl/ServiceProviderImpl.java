package com.myspringboot.provider.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.myspringboot.provider.ServiceProvider;

@Component
public class ServiceProviderImpl implements ServiceProvider {
	
	private static final Logger log = LoggerFactory.getLogger(ServiceProviderImpl.class);
	private static final String PASSSPECIALCHARS="!@>#$%+=-?^&*<";
	
    @Value("${app.otpchars}")
    private String otpChars;
    
    @Value("${app.passwordstring}")
    private String passwordString;
	
	@Autowired
	RestTemplate restTemplate;
	
	List<String> listOfString= Arrays.asList("Neeraj", "Nitish", "Dheeraj", "Ravi", "Umesh", "Virat");
	
	public ServiceProviderImpl() {
	}

	
	@Override
	public String welcomeUser(String username) {
      return "Hello "+username+ ", Welcome to SpringBoot using Gradle!";

	} 
	
	
	@Override
	public String generateOTP(int lotp) {
		log.info("generateOTP method called for otp length of: " +lotp);
		char otp[]= new char[lotp];
		Random random= new Random();
		for(int i=0; i<lotp; i++) {
			//log.info("OTP chars: " +otpChars);
			otp[i]= otpChars.charAt(random.nextInt(otpChars.length()));
			//log.info("char generated: " +otp[i]);
		}
		String otpGenerated= String.valueOf(otp);
		log.debug("OTP generated of size " +lotp+ ": " +otpGenerated);
		return otpGenerated;
	}

	
	@Override
	public String generateRandomPass(int length) {
		log.info("generateRandomPass method called for length of: " +length);
		char otp[]= new char[length];
		Random random= new Random();
		for(int i=0; i<length; i++) {
			if(!(i%2==0)) {
				otp[i]= PASSSPECIALCHARS.charAt(random.nextInt(PASSSPECIALCHARS.length()));
			}
			else {
				otp[i]= passwordString.charAt(random.nextInt(passwordString.length()));
			}
		}
		String otpGenerated= String.valueOf(otp);
		log.info("Random pass generated of size " +length+ ": " +otpGenerated);
		return otpGenerated;
	}

	@Override
	public boolean findString(String inputStr) {
		log.info("In findString: ");
		
		/*
		 * for(String str: listOfString) { if(inputStr.equalsIgnoreCase(str)) {
		 * log.info("found the string at :" +listOfString.indexOf(str)); } }
		 */
		return listOfString.stream().filter(str -> inputStr.equalsIgnoreCase(str)).findAny().isPresent();
		
	}

	@SuppressWarnings("unused")
	@Override
	public boolean isPrime(int num) {
		if(num<=1) {
			return false;
		}
		else
			
		for(int i=2; i<num; i++) {
			if(num%i==0) {
				log.info("input no is not prime: " +num);
				return false;
			}
			else
			{
				log.info("Input no. is prime: " +num);
				return true;
			}
		}
		
		
			return false;
		
		
		
	}
	
	public List<Integer> findFactors(int num){
		List<Integer> factorsList= new ArrayList<Integer>();
		for(int i=1; i<=num; i++ ) {
			if(num%i==0) {
				factorsList.add(i);
			}
		}
		log.info("Factors of number: " +num+ " are " +factorsList);
		return factorsList;
		
	}


	/*
	 * public String getWeatherReport() { String weatherServiceUrl=""; String
	 * ResponseString = restTemplate.getForObject(weatherServiceUrl, String.class);
	 * return ResponseString; }
	 */
	

	

}
