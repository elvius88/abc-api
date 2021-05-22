package py.com.roshka.abcconsulta.api.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;


public class APIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	private HttpStatus httpStatus;
	@Setter
	@Getter
	private String statusCode;
	@Setter
	@Getter
	private String message;
	

	public APIException(HttpStatus httpStatus, String statusCode, String message) {
		super();
		this.httpStatus = httpStatus;
		this.statusCode = statusCode;
		this.message = message;
	}


	public APIException(HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}
		
}
