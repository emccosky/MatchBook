package com.viperbots.matchbook2;

import java.util.ArrayList;

/**
 * Created by mccosky_890578 on 4/21/2016.
 */
public class Competition {

    public  int                 _id;
    private String              name;
    private String              location;
    private String              dateRange;
    private ArrayList<Integer>  teams;
    private ArrayList<Match>    matches;
    private ArrayList<Integer>  teamQP;
    private Boolean             statusQP =  false;
    private ArrayList<Integer>  teamRP;
    private Boolean             statusRP =  false;

    public Competition(int _id, String name, String location, String dateRange) {
        this._id = _id;
        this.name = name;
        this.location = location;
        this.dateRange = dateRange;
    }

    public Competition(int _id, String name, String location, String dateRange, ArrayList<Integer> teams) {
        this._id = _id;
        this.name = name;
        this.location = location;
        this.dateRange = dateRange;
        this.teams = teams;
    }

    public Competition(int _id, String name, String location, String dateRange, ArrayList<Integer> teams, ArrayList<Match> matches, ArrayList<Integer> teamQP, ArrayList<Integer> teamRP) {
        this._id = _id;
        this.name = name;
        this.location = location;
        this.dateRange = dateRange;
        this.teams = teams;
        this.matches = matches;
        this.teamQP = teamQP;
        this.teamRP = teamRP;
    }


    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDateRange() {
        return dateRange;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public ArrayList<Integer> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<Integer> teams) {
        this.teams = teams;
    }

    public ArrayList<Integer> getTeamQP() {
        return teamQP;
    }

    public void setTeamQP(ArrayList<Integer> teamQP) {
        this.teamQP = teamQP;
    }

    public ArrayList<Integer> getTeamRP() {
        return teamRP;
    }

    public void setTeamRP(ArrayList<Integer> teamRP) {
        this.teamRP = teamRP;
    }

    //Calculate the QP and RP for each team
    public void calcStats(){
        //for each team
        //generate team match list (if ot already generated)
        //calc QP RP
    }
}
