package com.gmf._5.vo;

import com.gmf._5.entity.Guestbook;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddLikeResponse {

    private Long guestbookId;
    private int likeCount;

    public AddLikeResponse(Guestbook guestbook) {
        this.guestbookId = guestbook.getId();
        this.likeCount = guestbook.getLikeCount();
    }
}
