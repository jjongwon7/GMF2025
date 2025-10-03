package com.gmf._5.entity;

import com.gmf._5.vo.MusicSummary;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Guestbook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String nickname;        // 닉네임
    private String comment;         // 코멘트
    private Boolean isDisplayed;    // 전시/미전시
    private int likeCount;         // 좋아요 수

    // 여기는 유사도가 가장 높은 것들을 보여주기 위함
    private String aiLyricsSummary;   // 가사 AI 요약
    private String title;           // 제목
    private String artist;          // 가수

    public Guestbook(String nickname, String comment, MusicSummary musicSummary) {
        this.nickname = nickname;
        this.comment = comment;
        this.isDisplayed = true;
        this.likeCount = 0;
        this.aiLyricsSummary = musicSummary.getAiLyricsSummary();
        this.title = musicSummary.getTitle();
        this.artist = musicSummary.getArtist();
    }

    public void addLike() {
        this.likeCount++;
    }

    public void cancelLike() {
        this.likeCount--;
    }

    public void updateDisplayed(boolean displayed) {
        this.isDisplayed = displayed;
    }
}
