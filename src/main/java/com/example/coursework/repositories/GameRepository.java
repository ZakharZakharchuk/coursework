package com.example.coursework.repositories;

import com.example.coursework.models.Game;
import com.example.coursework.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findGameByTeamsContains(Team team);

    @Modifying
    @Query("update Game.teams game set game = :teams where game.id = :id")
    void updateGame(Long id, List<Team> teams);
}
