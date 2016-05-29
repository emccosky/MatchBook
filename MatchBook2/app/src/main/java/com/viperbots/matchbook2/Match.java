package com.viperbots.matchbook2;

/**
 * Created by mccosky_890578 on 4/21/2016.
 */
public class Match {

    public  int     matchNum;
    private int     compID;
    private int     red1;
    private int     red2;
    private int     blue1;
    private int     blue2;

    private int     rScore;
    private int     bScore;

    private String  red1Notes;
    private String  red2Notes;
    private String  blue1Notes;
    private String  blue2Notes;

    public Match(int matchNum, int compID, int red1, int red2, int blue1, int blue2) {
        this.matchNum = matchNum;
        this.compID = compID;
        this.red1 = red1;
        this.red2 = red2;
        this.blue1 = blue1;
        this.blue2 = blue2;
    }

    public Match(int matchNum, int compID, int red1, int red2, int blue1, int blue2, int rScore, int bScore, String red1Notes, String red2Notes, String blue1Notes, String blue2Notes) {
        this.matchNum = matchNum;
        this.compID = compID;
        this.red1 = red1;
        this.red2 = red2;
        this.blue1 = blue1;
        this.blue2 = blue2;
        this.rScore = rScore;
        this.bScore = bScore;
        this.red1Notes = red1Notes;
        this.red2Notes = red2Notes;
        this.blue1Notes = blue1Notes;
        this.blue2Notes = blue2Notes;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public int getCompID() {
        return compID;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public int getRed1() {
        return red1;
    }

    public void setRed1(int red1) {
        this.red1 = red1;
    }

    public int getRed2() {
        return red2;
    }

    public void setRed2(int red2) {
        this.red2 = red2;
    }

    public int getBlue1() {
        return blue1;
    }

    public void setBlue1(int blue1) {
        this.blue1 = blue1;
    }

    public int getBlue2() {
        return blue2;
    }

    public void setBlue2(int blue2) {
        this.blue2 = blue2;
    }

    public int getrScore() {
        return rScore;
    }

    public void setrScore(int rScore) {
        this.rScore = rScore;
    }

    public int getbScore() {
        return bScore;
    }

    public void setbScore(int bScore) {
        this.bScore = bScore;
    }

    public String getRed1Notes() {
        return red1Notes;
    }

    public void setRed1Notes(String red1Notes) {
        this.red1Notes = red1Notes;
    }

    public String getRed2Notes() {
        return red2Notes;
    }

    public void setRed2Notes(String red2Notes) {
        this.red2Notes = red2Notes;
    }

    public String getBlue1Notes() {
        return blue1Notes;
    }

    public void setBlue1Notes(String blue1Notes) {
        this.blue1Notes = blue1Notes;
    }

    public String getBlue2Notes() {
        return blue2Notes;
    }

    public void setBlue2Notes(String blue2Notes) {
        this.blue2Notes = blue2Notes;
    }
}
