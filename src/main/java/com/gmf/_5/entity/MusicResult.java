package com.gmf._5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MusicResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String aiLyricsSummaryOneLine;   // 가사 AI 요약
    @Column(length = 300)
    private String aiLyricsSummaryThreeLines;   // 가사 AI 요약
    private int rankOrder;                 // 유사도 높은 순으로 랭킹 책정 (domain: 2, 3)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guestbook_id")
    private Guestbook guestbook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_track_id")
    private MusicTrack musicTrack;

    public MusicResult(String aiLyricsSummaryOneLine, String aiLyricsSummaryThreeLines, int rank,
        Guestbook guestbook, MusicTrack musicTrack) {

        this.aiLyricsSummaryOneLine = aiLyricsSummaryOneLine;
        this.aiLyricsSummaryThreeLines = aiLyricsSummaryThreeLines;
        this.rankOrder = rank;
        this.guestbook = guestbook;
        this.musicTrack = musicTrack;
    }
}