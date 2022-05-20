package com.example.coursework.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date gameDate;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "games_teams",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;


    public Game() {
    }

    public Game(List<Team> teams) {
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

//    public Date getGameDate() {
//        return gameDate;
//    }
//
//    public void setGameDate(Date gameDate) {
//        this.gameDate = gameDate;
//    }
}
