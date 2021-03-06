package com.leonardofadul.schoolSystem.services.validations;

import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.dto.StudentNewDTO;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class StudentInsertValidator implements ConstraintValidator<StudentInsert, StudentNewDTO> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void initialize(StudentInsert ann) {
    }

    @Override
    public boolean isValid(StudentNewDTO objNewDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Student aux = studentRepository.findByEmail(objNewDto.getEmail());
        if(aux != null){
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
