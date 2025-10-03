package com.gmf._5.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CommonCode {
    // SUCCESS
    SUCCESS(200, 200, "성공"),

    // FAIL
    FAIL(500, -1, "실패. 알 수 없는 오류"),

    // -1000: Guestbook
    NONEXISTENT_GUESTBOOK(400, -1000, "존재하지 않는 방명록 ID입니다."),

    // -2000: MusicTrack
    NONEXISTENT_MUSIC_TRACK(400, -2000, "존재하지 않는 곡 정보입니다."),

    // -3000: MusicResult
    EXCEED_SUMMARY_LIMIT(400, -3000, "요약 리스트는 최대 3개까지만 등록 가능합니다.");

    private int status;
    private int code;
    private String message;

    CommonCode(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
