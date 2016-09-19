package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {

	private final static String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final static Pattern EMAIL_COMPILED_PATTERN = Pattern
			.compile(EMAIL_PATTERN);

	public void validate(FacesContext fc, UIComponent c, Object o)
			throws ValidatorException {

		if (o == null || "".equals((String) o)) {
			FacesMessage msg = new FacesMessage("No email value!",
					"Email must be: *@*.*");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
		// The email matcher
		Matcher matcher = EMAIL_COMPILED_PATTERN.matcher((String) o);
		if (!matcher.matches()) {// Email doesn't match
			FacesMessage msg = new FacesMessage("Invalid email value!",
					"Email must be: *@*.*");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}