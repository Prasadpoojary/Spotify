package com.prasad.spotify.dau;

import com.prasad.spotify.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist,Integer> {

    @Query(value = "select a.id,a.name,a.dob,avg(r.rate)  from artist a inner join song s  on a.id=s.artist_id  left join rating r on s.id=r.song_id group by a.id;",nativeQuery = true)
    public List<Object> getAllWithRating();

    @Query(value = " select a.name,a.bio, a.dob,avg(r.rate)  from artist a inner join song s  on a.id=s.artist_id left join rating r on s.id=r.song_id where a.id=?1 group by r.song_id;",nativeQuery = true)
    public List<Object> getArtist(int id);


}
