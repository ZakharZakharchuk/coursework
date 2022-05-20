package com.example.coursework.repositories;

import com.example.coursework.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
    Team findByName(String name);
}
