package com.prasad.spotify.model;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int rate;
    private int song_id;
    private int user_id;

    public Rating() {
    }

    public Rating(int id, int rate, int song_id, int user_id) {
        this.id = id;
        this.rate = rate;
        this.song_id = song_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
