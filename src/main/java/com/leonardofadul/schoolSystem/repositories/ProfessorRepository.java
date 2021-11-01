package com.leonardofadul.schoolSystem.repositories;

import com.leonardofadul.schoolSystem.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    @Transactional(readOnly = true)
    Professor findByEmail(String email);
}
