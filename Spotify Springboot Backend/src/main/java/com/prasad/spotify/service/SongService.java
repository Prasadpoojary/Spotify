package com.prasad.spotify.service;

import com.prasad.spotify.dau.ArtistRepository;
import com.prasad.spotify.dau.RatingRepository;
import com.prasad.spotify.dau.SongRepository;
import com.prasad.spotify.dau.UserRepository;
import com.prasad.spotify.model.Artist;
import com.prasad.spotify.model.Rating;
import com.prasad.spotify.model.Song;
import com.prasad.spotify.model.SongResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class SongService
{
    @Autowired
    SongRepository songRepository;

    @Autowired
    ArtistRepository artistRepository;


    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UserRepository userRepository;



    public ResponseEntity<?> addSong(Song song)
    {
        this.songRepository.save(song);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> addArtist(Artist artist)
    {
        this.artistRepository.save(artist);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> rate(Rating rating)
    {
        Rating rate = this.ratingRepository.getBySongAndUser(rating.getSong_id(),rating.getUser_id());

        if(rate==null)
        {
            this.ratingRepository.save(rating);
        }
        else
        {
            rate.setRate(rating.getRate());
            this.ratingRepository.save(rate);
        }

        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<List<Object>> allSong(String search)
    {
        if(search!=null)
        {
            return new ResponseEntity<>(this.songRepository.searchAllSongList(search),HttpStatus.OK);
        }
        return  new ResponseEntity<>(this.songRepository.allSongList(),HttpStatus.OK);
    }

    public ResponseEntity<List<Object>> allArtist()
    {
        return  new ResponseEntity<>(this.artistRepository.getAllWithRating(),HttpStatus.OK);
    }

    public ResponseEntity<List<Object>> getArtistSongs(int artist)
    {
        return  new ResponseEntity<>(this.songRepository.getArtistSongs(artist),HttpStatus.OK);
    }

    public ResponseEntity<List<Object>> getArtist(int id)
    {
        return  new ResponseEntity<>(this.artistRepository.getArtist(id),HttpStatus.OK);
    }

    public ResponseEntity<SongResponse> songDetails(int id,String username)
    {
        int nextSong,prevSong;
        Song song=this.songRepository.findById(id).get();
        String artist=this.artistRepository.findById(song.getArtist_id()).get().getName();
        int user_id=this.userRepository.findByUsername(username).getId();
        double rating = this.ratingRepository.getBySongAndUser(id,user_id)!=null ? this.ratingRepository.getBySongAndUser(id,user_id).getRate() : 0;
        List<Integer> songIdList= this.songRepository.getAllId();

        if(songIdList.indexOf(id)==songIdList.size()-1)
        {
            nextSong=songIdList.get(0);
        }
        else
        {
            nextSong=songIdList.get(songIdList.indexOf(id)+1);
        }

        if(songIdList.indexOf(id)==0)
        {
            prevSong=songIdList.get(songIdList.size()-1);
        }
        else
        {
            prevSong=songIdList.get(songIdList.indexOf(id)-1);
        }

        songIdList.indexOf(id);
        SongResponse songResponse=new SongResponse(song.getName(),song.getMusic(),artist,rating,nextSong,prevSong);

        return new ResponseEntity<SongResponse>(songResponse,HttpStatus.OK);
    }
}
