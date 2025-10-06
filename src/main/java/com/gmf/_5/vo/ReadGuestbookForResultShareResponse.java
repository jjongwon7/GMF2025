package com.gmf._5.vo;

import com.gmf._5.entity.Guestbook;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadGuestbookForResultShareResponse {

    private String nickname;
    private String comment;
    private List<ReadMusicSummaryResponse> readMusicSummaryResponses;

    public ReadGuestbookForResultShareResponse(Guestbook guestbook, List<MusicSummary> musicSummaryList) {
        this.nickname = guestbook.getNickname();
        this.comment = guestbook.getComment();

        this.readMusicSummaryResponses = musicSummaryList.stream()
            .map(ReadGuestbookForResultShareResponse.ReadMusicSummaryResponse::new)
            .collect(Collectors.toList());
    }

    @Getter
    @NoArgsConstructor
    static class ReadMusicSummaryResponse {
        private String title;
        private String artist;
        private String aiLyricsSummary;

        public ReadMusicSummaryResponse(MusicSummary musicSummary) {
            this.title = musicSummary.getTitle();
            this.artist = musicSummary.getArtist();
            this.aiLyricsSummary = musicSummary.getAiLyricsSummaryThreeLines();
        }
    }
}
