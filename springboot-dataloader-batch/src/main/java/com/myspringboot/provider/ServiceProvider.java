package com.myspringboot.provider;

import java.util.List;

public interface ServiceProvider {

	String welcomeUser(String username);

	String generateOTP(int lotp);

	String generateRandomPass(int length);

	boolean findString(String inputStr);

	boolean isPrime(int n);
	
	List<Integer> findFactors(int num);

}
