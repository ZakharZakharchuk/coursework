package com.example.coursework.services.game;

import com.example.coursework.dto.GameDTO;
import com.example.coursework.models.Game;
import com.example.coursework.models.Result;
import com.example.coursework.models.Team;
import com.example.coursework.repositories.GameRepository;
import com.example.coursework.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;

    public GameServiceImpl(GameRepository gameRepository, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public List<GameDTO> listOfAllGames() {
        List<Game> games = gameRepository.findAll();
        return games.stream().map(this::mapGame).collect(Collectors.toList());
    }

    @Override
    public boolean addGame(GameDTO gameDTO) {
        Team firstTeam = teamRepository.findByName(gameDTO.getFirstTeam());
        Team secondTeam = teamRepository.findByName(gameDTO.getSecondTeam());
        Date gameDate = gameDTO.getGameDate();
        gameRepository.save(new Game(gameDate, List.of(firstTeam, secondTeam)));
        return true;
    }

    @Override
    public List<GameDTO> findByTeam(String name) {
        if (name == null || name.isEmpty())
            return listOfAllGames();
        else {
            Team team = teamRepository.findByName(name);
            return gameRepository.findGameByTeamsContains(team).stream().map(this::mapGame).collect(Collectors.toList());
        }
    }

    @Override
    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id).orElse(null);
        game.getTeams().clear();
        gameRepository.deleteById(id);
    }

    @Override
    public GameDTO findById(Long id) {
        //TODO what is it
        return mapGame(Objects.requireNonNull(gameRepository.findById(id).orElse(null)));
    }

    @Transactional
    public void updateGame(GameDTO gameDTO) {
        System.out.println(gameDTO.getGameDate());
        Team firstTeam = teamRepository.findByName(gameDTO.getFirstTeam());
        Team secondTeam = teamRepository.findByName(gameDTO.getSecondTeam());
        List<Team> teams = List.of(firstTeam, secondTeam);
        deleteGame(gameDTO.getId());
        gameRepository.save(new Game(gameDTO.getId(), gameDTO.getGameDate(), teams, new Result(teamRepository.findByName(gameDTO.getWinner()))));
    }

    private GameDTO mapGame(Game game) {
        Date gameDate = game.getGameDate();
        Long id = game.getId();
        List<Team> teams = game.getTeams().stream().toList();
        String firstTeam = teams.get(0).getName();
        String secondTeam = teams.get(1).getName();
        Result result = game.getResult();
        if (result != null)
            return new GameDTO(id, gameDate, firstTeam, secondTeam, result.getWinner().getName());
        else{
            return new GameDTO(id, gameDate, firstTeam, secondTeam, null);
        }

    }

}
