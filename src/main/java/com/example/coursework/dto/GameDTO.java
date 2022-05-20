package com.example.coursework.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class GameDTO {
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date gameDate;
    private Long id;
    private String firstTeam;
    private String secondTeam;

    public GameDTO(Date gameDate, String firstTeam, String secondTeam) {
        this.gameDate = gameDate;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public GameDTO(Long id, String firstTeam, String secondTeam) {
        this.id = id;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
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
}
