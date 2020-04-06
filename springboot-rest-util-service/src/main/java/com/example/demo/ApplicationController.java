package com.example.demo;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.delegate.ApplicationDelegate;

@RestController
@RequestMapping(value="application")
public class ApplicationController {

	@Autowired
	ApplicationDelegate applicationDelegate;
	
	
	
	@RequestMapping(value= "/",  method = RequestMethod.GET)
	public String welcomeUser(@RequestParam(name="userName") Optional <String> user) {
		if(user.isPresent()){
			return "Hello " +user.get();
		}
		return "Hello Guest!";
	}
	
		@RequestMapping(value="/getLocale/{attributename}",  method = RequestMethod.GET)
		public String getLocalisedContent(@PathVariable(required = true, name="attributename")  String attributeName ) {
			
			return applicationDelegate.getLocalisedResponse(attributeName, StringUtils.EMPTY);
	}
		
		@RequestMapping(value="/getLocale/{attributename}/{languagecode}",  method = RequestMethod.GET ,produces = MediaType.TEXT_PLAIN_VALUE)
		public String getLocalisedContentBasedOnLanguage(@PathVariable(required = true, name="attributename")  String attributeName,
				                                         @PathVariable(required = true, name="languagecode")  String languageCode) {
			System.out.println(" attr:: " +attributeName+ " lang:: " +languageCode);
			return applicationDelegate.getLocalisedResponse(attributeName, languageCode);
	}
	
}
