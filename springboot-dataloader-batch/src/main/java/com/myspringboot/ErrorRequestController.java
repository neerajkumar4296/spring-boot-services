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
	}

	@RequestMapping(value=ERROR_PATH, produces = MediaType.TEXT_HTML_VALUE)
	public String handleError(HttpServletRequest request) {
	    
	    Map<String, Object> responseStatus= super.getErrorAttributes(request, false);
			return "<html><center><b>"+"<h3 " + "style=" + "color:Red;" + ">"
			+"uri path:: "+"<i>"+responseStatus.get("path")+"</i>" +" is not mapped || failed with response code:: "
			+"<i>"+responseStatus.get("status")+ " (" +responseStatus.get("error")+ ")"+"</i>"  +"</h3></b></center></html>";
	}

	  @Override
	  public String getErrorPath() {
	      return ERROR_PATH;
	  }
}
