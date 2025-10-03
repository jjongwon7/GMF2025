package com.gmf._5.controller;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import com.gmf._5.service.GuestbookService;
import com.gmf._5.vo.ReadGuestbooksIgnoringDisplayFlagResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final GuestbookService guestbookService;

    @GetMapping("")
    public String getGuestbooks(Model model) {

        ReadGuestbooksIgnoringDisplayFlagResponse response =
            guestbookService.readGuestbooksIgnoringDisplayFlagResponse(100);

        model.addAttribute("response", response);

        return "guestbook-list";
    }

    @PostMapping("/guestbook/updateDisplay")
    @ResponseBody
    public Map<String, Object> updateDisplayFlag(
        @RequestParam Long guestbookId,
        @RequestParam Boolean displayFlag) {

        guestbookService.updateDisplayFlag(guestbookId, displayFlag);

        return Map.of(
            "success", true,
            "id", guestbookId,
            "displayFlag", displayFlag
        );
    }
}
