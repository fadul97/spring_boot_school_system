package com.leonardofadul.schoolSystem.services;

import com.leonardofadul.schoolSystem.domain.Professor;
import com.leonardofadul.schoolSystem.domain.Student;
import com.leonardofadul.schoolSystem.repositories.ProfessorRepository;
import com.leonardofadul.schoolSystem.repositories.StudentRepository;
import com.leonardofadul.schoolSystem.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email);
        Professor professor = professorRepository.findByEmail(email);
        if(student == null){
            if(professor == null){
                throw new UsernameNotFoundException(email);
            } else{
                return new UserSS(professor.getId(), professor.getEmail(), professor.getPassword(), professor.getProfiles());
            }
        } else{
            return new UserSS(student.getId(), student.getEmail(), student.getPassword(), student.getProfiles());
        }
    }
}
