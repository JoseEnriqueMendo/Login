package com.example.myapplication;

public class Eventos {

    public int      time_elapsed;
    public int      extra;
    public int      team_id;
    public String   team_name;
    public int      player_id;
    public String   player_name;
    public int      assist_id;
    public String   assist_name;
    public String   type_event;
    public String   detail_event;
    public String   comments_event;

    public Eventos(int time_elapsed, int extra, int team_id, String team_name, int player_id, String player_name, int assist_id, String assist_name, String type_event, String detail_event, String comments_event) {
        this.time_elapsed = time_elapsed;
        this.extra = extra;
        this.team_id = team_id;
        this.team_name = team_name;
        this.player_id = player_id;
        this.player_name = player_name;
        this.assist_id = assist_id;
        this.assist_name = assist_name;
        this.type_event = type_event;
        this.detail_event = detail_event;
        this.comments_event = comments_event;
    }
}
