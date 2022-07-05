package com.prasad.spotify.model;


import java.io.Serializable;

public class SongBody implements Serializable {
    private String name;
    private int artistId;

    public SongBody()
    {

    }


    public SongBody(String name, int artistId) {
        this.name = name;
        this.artistId = artistId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }


}
