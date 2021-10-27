package com.leonardofadul.schoolSystem.repositories;

import com.leonardofadul.schoolSystem.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Transactional(readOnly = true)
    Student findByEmail(String email);
}
