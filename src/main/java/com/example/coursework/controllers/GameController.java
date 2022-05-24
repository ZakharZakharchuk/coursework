package com.example.coursework.controllers;

import com.example.coursework.dto.GameDTO;
import com.example.coursework.dto.TeamDTO;
import com.example.coursework.repositories.GameRepository;
import com.example.coursework.services.game.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {
    private final GameService gameService;
    private final GameRepository gameRepository;

    public GameController(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/")
    public String listOfAllTours(Model model) {
        model.addAttribute("teamDTO", new TeamDTO());
        model.addAttribute("games", gameService.listOfAllGames());
        return "game/games";
    }

    @GetMapping("/add")
    public String addGame(Model model) {
        model.addAttribute("gameDTO", new GameDTO());
        return "game/new";
    }

    @PostMapping("/add")
    public String addGame(GameDTO gameDTO) {
        gameService.addGame(gameDTO);
        return "redirect:/";
    }

    @PostMapping("/find")
    public String findGame(TeamDTO teamDTO, Model model) {
        System.out.println(teamDTO.getName());
        model.addAttribute("games", gameService.findByTeam(teamDTO.getName()));
        return "game/games";
    }

    @GetMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateGame(@PathVariable Long id, Model model) {
        model.addAttribute("gameDTO", new GameDTO());
        model.addAttribute("game", gameService.findById(id));
        return "game/update";
    }

    @PostMapping("/update")
    public String updateGame(GameDTO gameDTO) {
        gameService.updateGame(gameDTO);
        return "redirect:/";
    }
}
