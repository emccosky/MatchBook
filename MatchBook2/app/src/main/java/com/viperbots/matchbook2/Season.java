package com.viperbots.matchbook2;

import java.util.ArrayList;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mccosky_890578 on 5/16/2016.
 */
public class Season {

    int year;
    String gameName;
    ArrayList<Team> teams;
    ArrayList<Competition> competitions;

    public Season(int yr, String game){
        year = yr;
        gameName = game;
    }

    public Season(int yr, String game, ArrayList<Team> teamList){
        year = yr;
        gameName = game;
        teams = teamList;
    }

    public Season(int yr, String game, ArrayList<Team> teamList, ArrayList<Competition> comps){
        year = yr;
        gameName = game;
        teams = teamList;
        competitions = comps;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(ArrayList<Competition> competitions) {
        this.competitions = competitions;
    }
}

