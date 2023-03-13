package webapp.code.spring.Util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import webapp.code.spring.Model.Person;

@Component
public class PersonValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz)
    {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {

    }
}
