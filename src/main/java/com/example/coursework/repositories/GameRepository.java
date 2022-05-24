package com.example.coursework.repositories;

import com.example.coursework.models.Game;
import com.example.coursework.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findGameByTeamsContains(Team team);
}
