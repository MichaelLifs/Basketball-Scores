package com.example.basketball;

public class Games {

    String date;
    String city;
    String teamA;
    String teamB;
    int scoreTeamA;
    int scoreTeamB;
    int id;

    public Games() {

    }

    public Games(String date, String city, String teamA, String teamB, int scoreTeamA, int scoreTeamB) {
        this.date = date;
        this.city = city;
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreTeamA = scoreTeamA;
        this.scoreTeamB = scoreTeamB;
    }

    public Games(int id,String date, String city, String teamA, String teamB, int scoreTeamA, int scoreTeamB) {
        this.id = id;
        this.date = date;
        this.city = city;
        this.teamA = teamA;
        this.teamB = teamB;
        this.scoreTeamA = scoreTeamA;
        this.scoreTeamB = scoreTeamB;
    }

    public int getId() { return  id; }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public int getScoreTeamA() {
        return scoreTeamA;
    }

    public int getScoreTeamB() {
        return scoreTeamB;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }

    public void setScoreTeamB(int scoreTeamB) {
        this.scoreTeamB = scoreTeamB;
    }
}
