package py.com.roshka.abcconsulta.api.messages;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ApiMessage {
	
	@Value("already-exist")
	private String alreadyExistMessage;
	
	@Value("internal-server-error")
	private String internalServerError;

}
