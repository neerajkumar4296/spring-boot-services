package com.example.demo;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.delegate.ApplicationDelegate;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ApplicationController.class, secure = false)
public class ApplicationsControllerTest {
	

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ApplicationDelegate applicationDelegate;
	
	private static final String  attribute="hobby";
	private static final String  languageCode="fr";
	private static final String  french_value_hobby="loisir";
	
	@Test
	public void test_getLocalisedContentBasedOnLanguage() throws Exception {
		
		when(this.applicationDelegate.getLocalisedResponse(attribute, languageCode)).thenReturn(french_value_hobby);
		
		this.mockMvc.perform( MockMvcRequestBuilders.get( "/application/getLocale/{attributename}/{languagecode}", attribute, languageCode )
				.accept( MediaType.TEXT_PLAIN_VALUE ) )
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().string(containsString(french_value_hobby.substring(0, 2))));
	}

}
