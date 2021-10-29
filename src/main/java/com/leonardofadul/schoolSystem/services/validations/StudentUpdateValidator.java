package com.leonardofadul.schoolSystem.services.validations;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.dto.StudentDTO;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentUpdateValidator implements ConstraintValidator<StudentUpdate, StudentDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void initialize(StudentUpdate ann) {
    }

    @Override
    public boolean isValid(StudentDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId =Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Student aux = studentRepository.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email already exists."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
