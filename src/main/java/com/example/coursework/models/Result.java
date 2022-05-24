package com.example.coursework.models;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "teams_id")
    private Team winner;

    public Result() {
    }

    public Result(Team winner) {
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }
}
