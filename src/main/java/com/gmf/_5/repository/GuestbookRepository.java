package com.gmf._5.repository;

import com.gmf._5.entity.Guestbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {

    Page<Guestbook> findByIsDisplayedTrue(Pageable pageable);
    Page<Guestbook> findByIdLessThanAndIsDisplayedTrue(Long lastGuestbookId, Pageable pageable);
}
