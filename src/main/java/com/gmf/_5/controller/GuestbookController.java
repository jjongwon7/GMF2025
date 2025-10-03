package com.gmf._5.controller;

import static com.gmf._5.common.constant.Constant.DEFAULT_PAGE_SIZE;
import static com.gmf._5.common.constant.Constant.SORT_BY_LATEST;

import com.gmf._5.service.GuestbookService;
import com.gmf._5.vo.CreateGuestbookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guestbook")
public class GuestbookController {

    private final GuestbookService guestbookService;

    /**
     * 결과페이지 - 방명록에 저장
     */
    @PostMapping
    public ResponseEntity<?> createGuestbook(@RequestBody CreateGuestbookRequest request) {
        return new ResponseEntity<>(
            guestbookService.createGuestbook(request.getNickname(), request.getComment(),
                request.getSummaryList()),
            HttpStatus.OK
        );
    }

    /**
     * 방명록페이지 - 최신순 / 좋아요 순 페이지네이션
     */
    @GetMapping
    public ResponseEntity<?> getGuestbooks(
        @RequestParam(required = false) Long lastGuestbookId,
        @RequestParam(defaultValue = SORT_BY_LATEST) String sortBy) {

        return new ResponseEntity<>(
            guestbookService.readGuestbooksResponse(DEFAULT_PAGE_SIZE, lastGuestbookId, sortBy),
            HttpStatus.OK
        );
    }

    /**
     * 방명록페이지 - 내 결과 공유하기
     */
    @GetMapping("{guestbookId}")
    public ResponseEntity<?> getGuestbook(@PathVariable Long guestbookId) {
        return new ResponseEntity<>(
            guestbookService.readGuestbook(guestbookId),
            HttpStatus.OK
        );
    }

    /**
     * 방명록페이지 - 좋아요 / 취소
     */
    @PostMapping("{guestbookId}/likes")
    public ResponseEntity<?> addLike(@PathVariable Long guestbookId) {
        return new ResponseEntity<>(
            guestbookService.addLike(guestbookId),
            HttpStatus.OK
        );
    }
}
