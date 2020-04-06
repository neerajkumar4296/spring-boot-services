package com.myspringboot;

import java.util.Arrays;
import java.util.Base64;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.myspringboot.jsonviewer.JsonViewer;
import com.myspringboot.model.ModelApp;
import com.myspringboot.provider.ServiceProvider;

@RestController
@RequestMapping(value = "/app")
@EnableScheduling
public class AppController {

	private static final Logger log = LoggerFactory.getLogger(AppController.class);

	@Value("${app.cronexpression}")
	private String cronExpression;

	@Autowired
	ServiceProvider serviceProvider;
	
	

	@RequestMapping(value = "/welcome", produces = MediaType.TEXT_PLAIN_VALUE)
	public String welcomeUser(@RequestParam(value = "username", defaultValue = "Guest") String userName) {
		log.info("in AppController welcome user...");		
		return serviceProvider.welcomeUser(userName);
	}

	// generating OTP of specified size
	@RequestMapping(value = "/generate/getOTP", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String getOTP(@RequestParam (value = "lotp", defaultValue = "4") int lotp) {
		return "OTP Generated Successfully :: " + serviceProvider.generateOTP(lotp);
	}

	@RequestMapping(value = "/calculate/summarystatistics", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String calculateAverage() {

		List<Double> intList = Arrays.asList(3.587, 5.6, 8.712, 6.325, 7.64);

		DoubleSummaryStatistics dss = intList.stream().mapToDouble(x -> 2*x).summaryStatistics();
		log.info("Summary Statistics: " + dss);
		Double avg = dss.getAverage() > 9 ? +dss.getAverage() : Math.round(dss.getAverage());
		return "Average:: " + avg + " Count:: " + dss.getCount() + " maximum value:: " + dss.getMax()
				+ " minimum value:: " + dss.getMin() + " sum:: " + dss.getSum();
	}

	@RequestMapping(value = "/exclude/print", produces = MediaType.APPLICATION_JSON_VALUE)
	public String dontPrintSensitiveInfo() {
		ModelApp mapp = new ModelApp("User098", serviceProvider.generateRandomPass(4),"986845", "User", "098");
		// final String toprint= ReflectionToStringBuilder.toStringExclude(mapp,
		// Arrays.asList("password", "lastName"));
		final String toprint = ToStringBuilder.reflectionToString(mapp);
		log.info(toprint);
		return toprint;
	}

	@RequestMapping(value = "/find/{inputStr}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String findInputString(@PathVariable String inputStr) {
		log.info("In findInputString: ");
		String foundvalue;
		long startTimeFindString = System.currentTimeMillis();
		boolean isInputStrPresent = serviceProvider.findString(inputStr);
		long endTimeFindString = System.currentTimeMillis();
		long executionTimefindString = (endTimeFindString - startTimeFindString);
		log.info("findString execution time: " + executionTimefindString);
		if (isInputStrPresent) {
			foundvalue = inputStr.toUpperCase() + " found in the list";
		} else {
			foundvalue = inputStr.toUpperCase() + " not found in the list";
		}
		return foundvalue;
	}

	@RequestMapping(value = "/checkifprime/{number}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String checkIfPrime(@PathVariable("number") int num) {

		if (serviceProvider.isPrime(num)) {
			return "input number is prime: " + num;
		};
		return "input number is not prime: " + num;

	}

	@RequestMapping(value = "/findfactors/{number}", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String findFactorsOfANumber(@PathVariable("number") int num) {
		return "Factors of number "+num+ " are:: " + StringUtils.join(serviceProvider.findFactors(num), ",");

	}
	
	//@JsonView(JsonViewer.Internal.class)
	@RequestMapping(value = "/getobject", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ModelApp getObject() {
		log.info("in get object: " + getClass());
		return new ModelApp("User098", serviceProvider.generateRandomPass(4),"986845", "User", "098");
	}

	@RequestMapping(value = "/generate/randompass", produces = { MediaType.TEXT_PLAIN_VALUE })
	public String getRandomPassFromParams(@RequestParam(value = "lengthofpass", defaultValue = "4") int lengthOfPass) {
		log.info("in get object: " + getClass());
		return  serviceProvider.generateRandomPass(lengthOfPass);
	}
	


}