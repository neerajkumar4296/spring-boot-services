package com.myspringboot;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorRequestController extends AbstractErrorController {

	private static final String ERROR_PATH=  "/error";
	
	 public ErrorRequestController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
		// TODO Auto-generated constructor stub
	}



	@RequestMapping(value =ERROR_PATH, produces=MediaType.TEXT_HTML_VALUE)
	public String errorHandler(HttpServletRequest request) {
		//HttpStatus status= super.getStatus(request);
		Map<String, Object> errorattributes= super.getErrorAttributes(request, true);
		
		return "Url Pattern:: "+"<html><b>"+errorattributes.get("path")+ "</b></html>" +" is not mapped || failed with response code:: " +"<html><b>"+
		errorattributes.get("status")+ " (" +errorattributes.get("error")+ ")" + "</b></html>";
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return ERROR_PATH;
	}
	
}
