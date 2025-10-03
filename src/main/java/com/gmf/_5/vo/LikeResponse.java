package com.gmf._5.vo;

import com.gmf._5.entity.Guestbook;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeResponse {

    private Long guestbookId;
    private int likeCount;

    public LikeResponse(Guestbook guestbook) {
        this.guestbookId = guestbook.getId();
        this.likeCount = guestbook.getLikeCount();
    }
}
