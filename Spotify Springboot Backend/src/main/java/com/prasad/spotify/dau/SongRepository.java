package com.prasad.spotify.dau;

import com.prasad.spotify.model.Song;
import com.prasad.spotify.model.SongResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface SongRepository extends JpaRepository<Song,Integer> {

    @Query(value = "select s.id as song_id,s.upload_date as song_date,s.name as song_name,a.name as artist_name,s.thumbnail as thumbnail,s.music as music,avg(r.rate) as rating from Song s inner join Artist a  on s.artist_id=a.id left join Rating r on s.id=r.song_id group by s.id",nativeQuery = true)
    public List<Object> allSongList();


    @Query(value = "select s.id from song s",nativeQuery = true)
    public List<Integer> getAllId();

    @Query(value = "select s.id, s.name as song_name,s.thumbnail,s.upload_date,a.name as artist_name,avg(r.rate)  from song s inner join artist a  on s.artist_id=a.id inner join rating r on s.id=r.song_id where s.artist_id=?1 group by s.id ;",nativeQuery = true)
    public List<Object> getArtistSongs(int artist);

    @Query(value = "select s.id as song_id,s.upload_date as song_date,s.name as song_name,a.name as artist_name,s.thumbnail as thumbnail,s.music as music,avg(r.rate) as rating from Song s inner join Artist a  on s.artist_id=a.id left join Rating r on s.id=r.song_id where s.name like %?1% or a.name like %?1% group by s.id",nativeQuery = true)
    public List<Object>  searchAllSongList(String search);
}
