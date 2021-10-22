package com.leonardofadul.schoolSystem.repositories;

import com.leonardofadul.schoolSystem.domain.ClassGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGradeRepository extends JpaRepository<ClassGrade, Integer>{
}
