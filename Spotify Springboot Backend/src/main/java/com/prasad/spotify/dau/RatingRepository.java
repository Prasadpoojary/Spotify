package com.prasad.spotify.dau;

import com.prasad.spotify.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RatingRepository extends JpaRepository<Rating,Integer> {
    @Query(value = "select * from rating where user_id= ?2 and song_id=?1",nativeQuery = true)
    public Rating getBySongAndUser(int song_id, int user_id);
}
