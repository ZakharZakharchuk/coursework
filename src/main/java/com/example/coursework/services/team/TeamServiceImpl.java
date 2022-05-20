package com.example.coursework.services.team;

import com.example.coursework.dto.TeamDTO;
import com.example.coursework.models.Team;
import com.example.coursework.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<TeamDTO> listOfAllTeams() {
        List<Team>teams = teamRepository.findAll();
        return teams.stream().map(this::mapTeam).collect(Collectors.toList());
    }
    private TeamDTO mapTeam(Team team){
        return new TeamDTO(team.getName());
    }
}
