package com.gmf._5.repository;

import com.gmf._5.entity.MusicTrack;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicTrackRepository extends JpaRepository<MusicTrack, Long> {

    Optional<MusicTrack> findByTitleAndArtist(String title, String artist);
}
