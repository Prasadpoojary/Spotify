package com.prasad.spotify.model;

public class SongResponse
{

    private String song_name;
    private String music;
    private String artist_name;
    private double rating;
    private int nextSong;
    private int prevSong;

    public SongResponse(String song_name, String music, String artist_name, double rating, int nextSong, int prevSong) {
        this.song_name = song_name;
        this.music = music;
        this.artist_name = artist_name;
        this.rating = rating;
        this.nextSong = nextSong;
        this.prevSong = prevSong;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNextSong() {
        return nextSong;
    }

    public void setNextSong(int nextSong) {
        this.nextSong = nextSong;
    }

    public int getPrevSong() {
        return prevSong;
    }

    public void setPrevSong(int prevSong) {
        this.prevSong = prevSong;
    }
}
