package com.leonardofadul.schoolSystem.repositories;

import com.leonardofadul.schoolSystem.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
