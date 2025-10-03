package com.gmf._5.vo;

import com.gmf._5.entity.Guestbook;
import com.gmf._5.entity.MusicResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicSummary {

    private String title;
    private String artist;
    private String aiLyricsSummary;

    public MusicSummary(Guestbook guestbook) {
        this.title = guestbook.getTitle();
        this.artist = guestbook.getArtist();
        this.aiLyricsSummary = guestbook.getAiLyricsSummary();
    }

    public MusicSummary(MusicResult musicResult) {
        this.title = musicResult.getMusicTrack().getTitle();
        this.artist = musicResult.getMusicTrack().getArtist();
        this.aiLyricsSummary = musicResult.getAiLyricsSummary();
    }
}
