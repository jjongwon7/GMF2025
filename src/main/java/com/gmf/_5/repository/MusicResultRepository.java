package com.gmf._5.repository;

import com.gmf._5.entity.Guestbook;
import com.gmf._5.entity.MusicResult;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicResultRepository extends JpaRepository<MusicResult, Long> {

    @EntityGraph(attributePaths = "musicTrack")
    List<MusicResult> findByGuestbookOrderByRankAsc(Guestbook guestbook);
}
