package com.leonardofadul.schoolSystem.repositories;

import com.leonardofadul.schoolSystem.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
