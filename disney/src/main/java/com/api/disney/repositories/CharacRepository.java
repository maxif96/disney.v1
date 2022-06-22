package com.api.disney.repositories;

import com.api.disney.models.Charac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacRepository extends JpaRepository<Charac, Long> {
}