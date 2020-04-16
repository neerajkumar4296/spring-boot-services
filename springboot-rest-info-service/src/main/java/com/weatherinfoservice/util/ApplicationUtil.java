package com.weatherinfoservice.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;




public class ApplicationUtil {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationUtil.class);

	public static void main(String args[]) throws Exception {
		//Passenger passenger= new Passenger("Neeraj", "Kumar", "Patna", "Patn", "9955716929", 2);
		 //reverseString("");
		//generateBookingNumber(passenger);
		 
		/*
		 * Integer date=1584297611; System.out.println("human redable date:: "
		 * +LocalDateTime.ofEpochSecond( date, 0, ZoneOffset.ofHoursMinutes(5, 30)));
		 */
		System.out.println(getNumbersDivisibleBy(4,6, 126));
		String emailAddresses = "nice.neeraj09@gmail.com,nitishsingh356@gmail.com";
		Arrays.asList(emailAddresses.split(",")).stream().
		forEach(emailAddress-> System.out.println(emailAddress.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*")));
		
		List<String> emailAddressesMasked=Arrays.asList(emailAddresses.split(",")).stream().
				map(emailAddress-> emailAddress.replaceAll("(?<=.{4}).(?=[^@]*?@)", "*")).collect(Collectors.toList());
				System.out.println("sending email report to recipients:: " +emailAddressesMasked);
	}
	public static String getNumbersDivisibleBy(int divisor1, int divisor2, int dividend) {
		StringBuilder resultBuilder= new StringBuilder();
		StringBuilder divisibleByDiv1AndDiv2 = new StringBuilder();
		divisibleByDiv1AndDiv2.append("Numbers divisible by both "+divisor1+ " and " +divisor2 +" between 1-" +dividend+ " are:: ");
		
		StringBuilder divisibleByDiv1= new StringBuilder();
		divisibleByDiv1.append("Numbers divisible by "+divisor1+ " between 1-" +dividend+ " are:: ");
		StringBuilder divisibleByDiv2 = new StringBuilder();
		divisibleByDiv2.append("Numbers divisible by "+divisor2+ " between 1-" +dividend+ " are:: ");
		StringBuilder otherNumbers = new StringBuilder();
		otherNumbers.append("numbers not divisible by either "+divisor1+ " or " +divisor2+ " or both between 1-" +dividend+ " are:: ");
		
		
		for(int i=1; i<=dividend; i++) {
			if((i%divisor1==0) && (i%divisor2==0)) {
				divisibleByDiv1AndDiv2.append(i+" ");
			}
			else if (i%divisor1==0) {
				divisibleByDiv1.append(i+" ");
			}
			else if (i%divisor2==0) {
				divisibleByDiv2.append(i+" ");
			}
			
			else
				otherNumbers.append(i+" ");
			
		}
		
		logger.debug("Successfully generated the divisible numbers" );
		return resultBuilder.append(divisibleByDiv1AndDiv2.toString()).append("\n")
				.append(divisibleByDiv1.toString()).append("\n")
				.append(divisibleByDiv2.toString()).append("\n")
				.append(otherNumbers.toString()).append("\n").toString();
		
	}

	public static String reverseString(String inputString) {
		String reversedString;
		if (StringUtils.isEmpty(inputString)) {
			logger.error("Error Occurred..Input String is blank:: ");
			return "Input String was not provided or is Blank::" ;
		} else {
			logger.info("Input String is :: " + inputString);
			StringBuilder reversedStringBuilder= new StringBuilder();
			for (int i = inputString.length() - 1; i >= 0; i--) {
				//reversedString = reversedString + inputString.charAt(i);
				reversedStringBuilder.append(inputString.charAt(i));
			}
			reversedString= reversedStringBuilder.toString();
			logger.info("Reversed String is:: " +reversedString);
		}
		return reversedString;

	}
	

	public static String generateOTP(int length) {

		String otpChars="0123456789";
		Random random = new Random();
		char[] otpArray= new char[length];
		for(int i=0; i<=length-1; i++) {
			otpArray[i]= otpChars.charAt(random.nextInt(otpChars.length()));
		}
		logger.debug("otp generated successfully");
		return String.valueOf(otpArray);
	}
	
}