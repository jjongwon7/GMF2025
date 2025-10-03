package com.gmf._5.entity;

import com.gmf._5.vo.MusicSummary;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MusicResult extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aiLyricsSummary;   // 가사 AI 요약
    private int rank;                 // 유사도 높은 순으로 랭킹 책정 (domain: 2, 3)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guestbook_id")
    private Guestbook guestbook;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_track_id")
    private MusicTrack musicTrack;

    public MusicResult(String aiLyricsSummary, int rank, Guestbook guestbook,
        MusicTrack musicTrack) {

        this.aiLyricsSummary = aiLyricsSummary;
        this.rank = rank;
        this.guestbook = guestbook;
        this.musicTrack = musicTrack;
    }
}