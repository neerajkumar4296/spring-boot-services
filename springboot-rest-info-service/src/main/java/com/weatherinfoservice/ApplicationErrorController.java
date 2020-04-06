package com.weatherinfoservice;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;


public class ApplicationErrorController extends AbstractErrorController {

	public ApplicationErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
	    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    Object message= request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
	    Object path= request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        
	        if(statusCode == HttpStatus.BAD_REQUEST.value()) {
	            return "ERRRO-400 "+ "messages:: " +message.toString()+ " Path:: " +path;
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "ERRRO-500 "+ "messages:: " +message.toString()+ " Path:: " +path;
	        }
	    }
	    return "error";
	}

	  @Override
	  public String getErrorPath() {
	      return "/error";
	  }
}
