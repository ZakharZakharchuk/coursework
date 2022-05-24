package com.example.coursework.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class GameDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date gameDate;
    private Long id;
    private String firstTeam;
    private String secondTeam;
    private String winner;

    public GameDTO(Date gameDate, String firstTeam, String secondTeam, String winner) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.winner = winner;
        this.gameDate = gameDate;
    }

    public GameDTO(Date gameDate, String firstTeam, String secondTeam) {
        this.gameDate = gameDate;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public GameDTO(Long id, Date gameDate, String firstTeam, String secondTeam, String winner) {
        this.id = id;
        this.gameDate = gameDate;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.winner = winner;
    }

    public GameDTO() {
    }

    public String getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(String firstTeam) {
        this.firstTeam = firstTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
