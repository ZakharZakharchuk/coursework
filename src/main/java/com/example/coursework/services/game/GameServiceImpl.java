package com.example.coursework.services.game;

import com.example.coursework.dto.GameDTO;
import com.example.coursework.models.Game;
import com.example.coursework.models.Team;
import com.example.coursework.repositories.GameRepository;
import com.example.coursework.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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
        gameRepository.save(new Game(List.of(firstTeam, secondTeam)));
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
        System.out.println(gameDTO.getId());
        System.out.println(gameDTO.getFirstTeam());
        System.out.println(gameDTO.getSecondTeam());
        Team firstTeam = teamRepository.findByName(gameDTO.getFirstTeam());
        Team secondTeam = teamRepository.findByName(gameDTO.getSecondTeam());
        List<Team> teams = List.of(firstTeam, secondTeam);
        gameRepository.updateGame(gameDTO.getId(), teams);
    }

    private GameDTO mapGame(Game game) {
//        Date gameDate = game.getGameDate();
        Long id = game.getId();
        List<Team> teams = game.getTeams().stream().toList();
        String firstTeam = teams.get(0).getName();
        String secondTeam = teams.get(1).getName();
        return new GameDTO(id, firstTeam, secondTeam);
    }
}
