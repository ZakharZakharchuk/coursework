package com.example.coursework.controllers;

import com.example.coursework.services.team.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public String listOfTeams(Model model){
        model.addAttribute("teams", teamService.listOfAllTeams());
        return "teams";
    }
}
