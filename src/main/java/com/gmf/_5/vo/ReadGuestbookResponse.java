package com.gmf._5.vo;

import com.gmf._5.entity.Guestbook;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadGuestbookResponse {

    private String nickname;
    private String comment;
    private List<MusicSummary> summaryList;

    public ReadGuestbookResponse(Guestbook guestbook, List<MusicSummary> summaryList) {
        this.nickname = guestbook.getNickname();
        this.comment = guestbook.getComment();
        this.summaryList = summaryList;
    }
}
