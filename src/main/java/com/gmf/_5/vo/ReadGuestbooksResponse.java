package com.gmf._5.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmf._5.entity.Guestbook;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReadGuestbooksResponse {

    private List<ReadGuestbookResponse> readGuestbookResponses;

    public ReadGuestbooksResponse(List<Guestbook> guestbooks) {
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

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;      // 생성일

        // MusicTrack
        private String title;           // 제목
        private String artist;          // 가수
        private String aiLyricsSummary;


        public ReadGuestbookResponse(Guestbook guestbook) {
            this.id = guestbook.getId();
            this.nickname = guestbook.getNickname();
            this.comment = guestbook.getComment();
            this.likeCount = guestbook.getLikeCount();
            this.createTime = guestbook.getCreatedDate();
            this.title = guestbook.getTitle();
            this.artist = guestbook.getArtist();
            this.aiLyricsSummary = guestbook.getAiLyricsSummaryOneLine();
        }
    }
}
