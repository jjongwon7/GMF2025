package com.gmf._5.vo;

import com.gmf._5.entity.Guestbook;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadGuestbooksIgnoringDisplayFlagResponse {

    private List<ReadGuestbookResponse> readGuestbookResponses;

    public ReadGuestbooksIgnoringDisplayFlagResponse(List<Guestbook> guestbooks) {
        this.readGuestbookResponses = guestbooks.stream()
            .map(ReadGuestbookResponse::new)
            .collect(Collectors.toList());
    }

    @Getter
    @NoArgsConstructor
    static class ReadGuestbookResponse {
        // Guestbook
        private Long id;                // guestbookId
        private String nickname;        // 닉네임
        private String comment;         // 코멘트
        private int likeCount;          // 좋아요 수
        private Boolean isDisplayed;    // 전시/미전시
        private String createdDate;

        public ReadGuestbookResponse(Guestbook guestbook) {
            this.id = guestbook.getId();
            this.nickname = guestbook.getNickname();
            this.comment = guestbook.getComment();
            this.likeCount = guestbook.getLikeCount();
            this.isDisplayed = guestbook.getIsDisplayed();
            this.createdDate = guestbook.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }
}
