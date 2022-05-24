package com.example.coursework.services.game;

import com.example.coursework.dto.GameDTO;

import java.util.List;

public interface GameService {
    List<GameDTO> listOfAllGames();

    boolean addGame(GameDTO gameDTO);

    List<GameDTO> findByTeam(String name);

    void deleteGame(Long id);

    GameDTO findById(Long id);

    void updateGame(GameDTO gameDTO);
}
