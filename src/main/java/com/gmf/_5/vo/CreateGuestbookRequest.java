package com.gmf._5.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGuestbookRequest {

    private String nickname;
    private String comment;
    private List<MusicSummary> summaryList;

}