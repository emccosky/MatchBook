package com.viperbots.matchbook;

import java.util.ArrayList;

/**
 * Created by mccosky_890578 on 4/13/2016.
 */
public class Team {

    public  int                 teamID;
    private String              teamName;
    private ArrayList<Match>    teamMatches;
    private boolean             matchListGen = false;
    private ArrayList<Integer>  compIDs;


    public Team(int teamID, String teamName) {
        this.teamID = teamID;
        this.teamName = teamName;
    }

    public int getTeamID() {
        return teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Match> getTeamMatches() {
        return teamMatches;
    }

    public void setTeamMatches(ArrayList<Match> teamMatches) {
        this.teamMatches = teamMatches;
    }

    public boolean isMatchListGen() {
        return matchListGen;
    }

    public void setMatchListGen(boolean matchListGen) {
        this.matchListGen = matchListGen;
    }

    public ArrayList<Integer> getCompIDs() {
        return compIDs;
    }

    public void setCompIDs(ArrayList<Integer> compIDs) {
        this.compIDs = compIDs;
    }
}
