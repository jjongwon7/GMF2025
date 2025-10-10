package com.gmf._5.service;

import static com.gmf._5.common.constant.Constant.DEFAULT_PAGE_SIZE;
import static com.gmf._5.common.constant.Constant.SORT_BY_LIKES;

import com.gmf._5.common.exception.CustomException;
import com.gmf._5.common.response.CommonCode;
import com.gmf._5.entity.Guestbook;
import com.gmf._5.entity.MusicResult;
import com.gmf._5.entity.MusicTrack;
import com.gmf._5.repository.GuestbookRepository;
import com.gmf._5.repository.MusicResultRepository;
import com.gmf._5.repository.MusicTrackRepository;
import com.gmf._5.vo.LikeResponse;
import com.gmf._5.vo.MusicSummary;
import com.gmf._5.vo.ReadGuestbookForResultShareResponse;
import com.gmf._5.vo.ReadGuestbooksIgnoringDisplayFlagResponse;
import com.gmf._5.vo.ReadGuestbooksResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;
    private final MusicTrackRepository musicTrackRepository;
    private final MusicResultRepository musicResultRepository;

    public ReadGuestbooksResponse createGuestbook(String nickname, String comment,
        List<MusicSummary> summaryList) {

        if (summaryList.size() > 3) {
            throw new CustomException(CommonCode.EXCEED_SUMMARY_LIMIT);
        }

        Map<MusicSummary, MusicTrack> map = new LinkedHashMap<>();

        for (MusicSummary musicSummary : summaryList) {
            map.put(musicSummary,
                readMusicTrack(musicSummary.getTitle(), musicSummary.getArtist()));
        }

        Guestbook savedGuestbook = null;

        List<MusicSummary> keys = new ArrayList<>(map.keySet());

        for (int rank = 1; rank <= map.size(); rank++) {
            MusicSummary musicSummary = keys.get(rank - 1);
            MusicTrack musicTrack = map.get(musicSummary);

            if (rank == 1) {
                savedGuestbook = guestbookRepository.save(
                    new Guestbook(nickname, comment, musicSummary));
            } else {
                musicResultRepository.save(
                    new MusicResult(musicSummary.getAiLyricsSummaryOneLine(),
                        musicSummary.getAiLyricsSummaryThreeLines(), rank, savedGuestbook,
                        musicTrack));
            }
        }

        return readGuestbooksResponse(DEFAULT_PAGE_SIZE, null, "latest");
    }

    private MusicTrack readMusicTrack(String title, String artist) {
        return musicTrackRepository.findByTitleAndArtist(title, artist)
            .orElseThrow(
                () -> {
                    log.error("존재하지 않는 음악 트랙 - title: {}, artist: {}", title, artist);
                    return new CustomException(CommonCode.NONEXISTENT_MUSIC_TRACK);
                });
    }

    public ReadGuestbooksResponse readGuestbooksResponse(int pageSize, Long lastGuestbookId,
        String sortBy) {

        Sort sort = SORT_BY_LIKES.equals(sortBy)
            ? Sort.by(Sort.Order.desc("likeCount"), Sort.Order.desc("id"))
            : Sort.by(Direction.DESC, "id");

        return readGuestbooksSortedBy(pageSize, lastGuestbookId, sort);
    }

    private ReadGuestbooksResponse readGuestbooksSortedBy(int pageSize, Long lastGuestbookId,
        Sort sort) {

        PageRequest pageRequest = PageRequest.of(0, pageSize, sort);

        Page<Guestbook> page = (lastGuestbookId == null || lastGuestbookId < 1)
            ? guestbookRepository.findByIsDisplayedTrue(pageRequest)
            : guestbookRepository.findByIdLessThanAndIsDisplayedTrue(lastGuestbookId, pageRequest);

        return new ReadGuestbooksResponse(page.getContent());
    }

    public ReadGuestbooksIgnoringDisplayFlagResponse readGuestbooksIgnoringDisplayFlagResponse(
        int pageSize) {

        Sort sort = Sort.by(Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(0, pageSize, sort);

        Page<Guestbook> page = guestbookRepository.findAll(pageRequest);

        return new ReadGuestbooksIgnoringDisplayFlagResponse(page.getContent());
    }

    @Transactional
    public LikeResponse addLike(Long guestbookId) {

        Guestbook guestbook = guestbookRepository.findById(guestbookId)
            .orElseThrow(() -> new CustomException(CommonCode.NONEXISTENT_GUESTBOOK));

        guestbook.addLike();

        return new LikeResponse(guestbook);
    }

    @Transactional
    public LikeResponse cancelLike(Long guestbookId) {

        Guestbook guestbook = guestbookRepository.findById(guestbookId)
            .orElseThrow(() -> new CustomException(CommonCode.NONEXISTENT_GUESTBOOK));

        if (guestbook.getLikeCount() <= 0) {
            throw new CustomException(CommonCode.INVALID_LIKE_COUNT);
        }

        guestbook.cancelLike();

        return new LikeResponse(guestbook);
    }

    @Transactional
    public void updateDisplayFlag(Long guestbookId, Boolean displayFlag) {
        log.info("guestbookId");
        Guestbook guestbook = guestbookRepository.findById(guestbookId)
            .orElseThrow(() -> new CustomException(CommonCode.NONEXISTENT_GUESTBOOK));

        guestbook.updateDisplayed(displayFlag);
    }

    public ReadGuestbookForResultShareResponse readGuestbook(Long guestbookId) {
        Guestbook guestbook = guestbookRepository.findById(guestbookId)
            .orElseThrow(() -> new CustomException(CommonCode.NONEXISTENT_GUESTBOOK));

        List<MusicResult> musicResultList = musicResultRepository.findByGuestbookOrderByRankOrderAsc(
            guestbook);

        List<MusicSummary> musicSummaryList = new ArrayList<>();
        musicSummaryList.add(new MusicSummary(guestbook));

        if (musicResultList != null && !musicResultList.isEmpty()) {
            for (MusicResult musicResult : musicResultList) {
                musicSummaryList.add(new MusicSummary(musicResult));
            }
        }

        return new ReadGuestbookForResultShareResponse(guestbook, musicSummaryList);
    }
}
