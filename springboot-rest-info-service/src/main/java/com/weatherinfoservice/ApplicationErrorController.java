package com.weatherinfoservice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationErrorController extends AbstractErrorController {

	private static final String ERROR_PATH=  "/error";
	
	public ApplicationErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@RequestMapping(value=ERROR_PATH, produces = MediaType.TEXT_HTML_VALUE)
	public String handleError(HttpServletRequest request) {
	    
		Map<String, Object> responseStatus= super.getErrorAttributes(request, false);
		return "<html><center><b>"+"<h3 " + "style=" + "color:Red;" + ">"
		+"uri path:: "+"<i>"+responseStatus.get("path")+"</i>" +" is not mapped || failed with response code:: "
		+"<i>"+responseStatus.get("status")+ " (" +responseStatus.get("error")+ ")"+"</i>"  +"</h3>"+"<br/>"
		+"<p " + "style=" + "color:DodgerBlue;" + ">"+ "for service uri mapping info please access the uri path as :: " +"/rest-info-service/swagger-ui.html"+"</p>"
		+"</b></center></html>";
	}

	  @Override
	  public String getErrorPath() {
	      return ERROR_PATH;
	  }
}
