package com.leonardofadul.schoolSystem.repositories;

import com.leonardofadul.schoolSystem.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
