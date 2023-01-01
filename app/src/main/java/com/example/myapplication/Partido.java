package com.example.myapplication;
public class Partido {

    public int id_fixture;
    public  String  timestamp_fixture;
    public  int     id_venue;

    public  String  venue_name;
    public  String  status_long;
    public  int     id_league;

    public  String     name_league;
    public  int     home_id;
    public  String  home_name;
    public  String  home_logo;
    public  boolean home_winner;
    public  boolean away_winner;
    public  int     away_id;
    public  String  away_name;
    public  String  away_logo;
    public  int     home_goals;
    public  int     away_goals;

    @Override
    public String toString() {
        return "Partido{" +
                "id_fixture=" + id_fixture +
                ", timestamp_fixture='" + timestamp_fixture + '\'' +
                ", id_venue=" + id_venue +
                ", venue_name='" + venue_name + '\'' +
                ", status_long='" + status_long + '\'' +
                ", id_league=" + id_league +
                ", name_league='" + name_league + '\'' +
                ", home_id=" + home_id +
                ", home_name='" + home_name + '\'' +
                ", home_logo='" + home_logo + '\'' +
                ", home_winner=" + home_winner +
                ", away_id=" + away_id +
                ", away_name='" + away_name + '\'' +
                ", away_logo='" + away_logo + '\'' +
                ", home_goals=" + home_goals +
                ", away_goals=" + away_goals +
                '}';
    }

    public int getId_fixture() {
        return id_fixture;
    }

    public String getTimestamp_fixture() {
        return timestamp_fixture;
    }

    public int getId_venue() {
        return id_venue;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public String getStatus_long() {
        return status_long;
    }

    public int getId_league() {
        return id_league;
    }

    public String getName_league() {
        return name_league;
    }

    public int getHome_id() {
        return home_id;
    }

    public String getHome_name() {
        return home_name;
    }

    public String getHome_logo() {
        return home_logo;
    }

    public boolean isHome_winner() {
        return home_winner;
    }

    public boolean isAway_winner() {
        return away_winner;
    }

    public int getAway_id() {
        return away_id;
    }

    public String getAway_name() {
        return away_name;
    }

    public String getAway_logo() {
        return away_logo;
    }

    public int getHome_goals() {
        return home_goals;
    }

    public int getAway_goals() {
        return away_goals;
    }

    public Partido(int id_fixture, String timestamp_fixture, int id_venue, String venue_name, String status_long, int id_league, String name_league, int home_id, String home_name, String home_logo, boolean home_winner, int away_id, String away_name, String away_logo, boolean away_winner, int home_goals, int away_goals) {
        this.id_fixture = id_fixture;
        this.timestamp_fixture = timestamp_fixture;
        this.id_venue = id_venue;
        this.venue_name = venue_name;
        this.status_long = status_long;
        this.id_league = id_league;
        this.name_league = name_league;
        this.home_id = home_id;
        this.home_name = home_name;
        this.home_logo = home_logo;
        this.home_winner = home_winner;
        this.away_winner = away_winner;
        this.away_id = away_id;
        this.away_name = away_name;
        this.away_logo = away_logo;
        this.home_goals = home_goals;
        this.away_goals = away_goals;
    }
}