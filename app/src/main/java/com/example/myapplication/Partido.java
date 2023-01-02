package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Partido implements Parcelable {
    public int getTime_fixture() {
        return time_fixture;
    }

    public void setTime_fixture(int time_fixture) {
        this.time_fixture = time_fixture;
    }

    public int id_fixture;
    public  String  timestamp_fixture;
    public  int     id_venue;

    public  String  venue_name;
    public  String  status_long;
    public  int     id_league;

    public  String     name_league;

    public  String     pais_league;
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
    public  int     time_fixture;



    protected Partido(Parcel in) {
        id_fixture = in.readInt();
        timestamp_fixture = in.readString();
        id_venue = in.readInt();
        venue_name = in.readString();
        status_long = in.readString();
        id_league = in.readInt();
        name_league = in.readString();
        pais_league = in.readString();
        home_id = in.readInt();
        home_name = in.readString();
        home_logo = in.readString();
        home_winner = in.readByte() != 0;
        away_winner = in.readByte() != 0;
        away_id = in.readInt();
        away_name = in.readString();
        away_logo = in.readString();
        home_goals = in.readInt();
        away_goals = in.readInt();

        ciudad = in.readString();
        referee = in.readString();
    }

    public static final Creator<Partido> CREATOR = new Creator<Partido>() {
        @Override
        public Partido createFromParcel(Parcel in) {
            return new Partido(in);
        }

        @Override
        public Partido[] newArray(int size) {
            return new Partido[size];
        }
    };


    public String getCiudad() {
        return ciudad;
    }

    public void setId_fixture(int id_fixture) {
        this.id_fixture = id_fixture;
    }

    public void setTimestamp_fixture(String timestamp_fixture) {
        this.timestamp_fixture = timestamp_fixture;
    }

    public void setId_venue(int id_venue) {
        this.id_venue = id_venue;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public void setStatus_long(String status_long) {
        this.status_long = status_long;
    }

    public void setId_league(int id_league) {
        this.id_league = id_league;
    }

    public void setName_league(String name_league) {
        this.name_league = name_league;
    }

    public void setHome_id(int home_id) {
        this.home_id = home_id;
    }

    public void setHome_name(String home_name) {
        this.home_name = home_name;
    }

    public void setHome_logo(String home_logo) {
        this.home_logo = home_logo;
    }

    public void setHome_winner(boolean home_winner) {
        this.home_winner = home_winner;
    }

    public void setAway_winner(boolean away_winner) {
        this.away_winner = away_winner;
    }

    public void setAway_id(int away_id) {
        this.away_id = away_id;
    }

    public void setAway_name(String away_name) {
        this.away_name = away_name;
    }

    public void setAway_logo(String away_logo) {
        this.away_logo = away_logo;
    }

    public void setHome_goals(int home_goals) {
        this.home_goals = home_goals;
    }

    public void setAway_goals(int away_goals) {
        this.away_goals = away_goals;
    }



    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getReferee() {
        return referee;
    }

    public String getPais_league() {
        return pais_league;
    }

    public void setPais_league(String pais_league) {
        this.pais_league = pais_league;
    }

    public String   ciudad;

    public String   referee;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id_fixture);
        dest.writeString(timestamp_fixture);
        dest.writeInt(id_venue);
        dest.writeString(venue_name);
        dest.writeString(status_long);
        dest.writeInt(id_league);
        dest.writeString(name_league);
        dest.writeString(pais_league);
        dest.writeInt(home_id);
        dest.writeString(home_name);
        dest.writeString(home_logo);
        dest.writeByte((byte) (home_winner ? 1 : 0));
        dest.writeByte((byte) (away_winner ? 1 : 0));
        dest.writeInt(away_id);
        dest.writeString(away_name);
        dest.writeString(away_logo);
        dest.writeInt(home_goals);
        dest.writeInt(away_goals);

        dest.writeString(ciudad);
        dest.writeString(referee);
    }
}