package com.example.coursework.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gameDate;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "games_teams",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private Result result;


    public Game(Long id, Date gameDate, List<Team> teams, Result result) {
        this.id = id;
        this.gameDate = gameDate;
        this.teams = teams;
        this.result = result;
    }

    public Game(Long id, List<Team> teams, Result result) {
        this.id = id;
        this.teams = teams;
        this.result = result;
    }

    public Game(Date gameDate, List<Team> teams) {
        this.gameDate = gameDate;
        this.teams = teams;
    }

    public Game() {

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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
}
