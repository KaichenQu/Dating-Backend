package com.groupf.dating.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupf.dating.dto.PhotoRankResponse;
import com.groupf.dating.exception.IException;
import com.groupf.dating.repository.ProfileRequestRepository;
import com.groupf.dating.service.impl.PhotoRankingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhotoRankingServiceImplTest {

    @Mock
    private ClaudeApiService claudeApiService;

    @Mock
    private ProfileRequestRepository profileRequestRepository;

    private PhotoRankingServiceImpl photoRankingService;

    @BeforeEach
    void setUp() {
        photoRankingService = new PhotoRankingServiceImpl(
                claudeApiService, new ObjectMapper(), profileRequestRepository);
    }

    // ──────────── validation ────────────

    @Test
    void rankPhotos_throwsTooFew_whenLessThanTwoPhotos() {
        MockMultipartFile single = jpeg("photo1.jpg");

        assertThatThrownBy(() -> photoRankingService.rankPhotos(new MultipartFile[]{single}))
                .isInstanceOf(IException.class);
    }

    @Test
    void rankPhotos_throwsTooMany_whenMoreThanFivePhotos() {
        MultipartFile[] photos = new MultipartFile[6];
        for (int i = 0; i < 6; i++) photos[i] = jpeg("photo" + i + ".jpg");

        assertThatThrownBy(() -> photoRankingService.rankPhotos(photos))
                .isInstanceOf(IException.class);
    }

    @Test
    void rankPhotos_throwsInvalidFormat_forNonImageFile() {
        MockMultipartFile txt = new MockMultipartFile("photos", "doc.txt", "text/plain", new byte[100]);
        MockMultipartFile img = jpeg("photo.jpg");

        assertThatThrownBy(() -> photoRankingService.rankPhotos(new MultipartFile[]{txt, img}))
                .isInstanceOf(IException.class);
    }

    // ──────────── JSON parsing — plain JSON ────────────

    @Test
    void rankPhotos_parsesPlainJsonResponse() {
        String plainJson = """
                [
                  {"rank": 1, "score": 85, "reasoning": "Great lighting and smile"},
                  {"rank": 2, "score": 70, "reasoning": "Good composition"}
                ]""";
        when(claudeApiService.callClaudeApiWithVision(anyString(), anyString(), any()))
                .thenReturn(plainJson);

        PhotoRankResponse response = photoRankingService.rankPhotos(twoPhotos());

        assertThat(response.getRankedPhotos()).hasSize(2);
        assertThat(response.getRankedPhotos().get(0).getRank()).isEqualTo(1);
        assertThat(response.getRankedPhotos().get(0).getScore()).isEqualTo(85.0);
        assertThat(response.getRankedPhotos().get(0).getReasoning()).isEqualTo("Great lighting and smile");
        assertThat(response.getRankedPhotos().get(1).getRank()).isEqualTo(2);
    }

    // ──────────── JSON parsing — markdown-wrapped ────────────

    @Test
    void rankPhotos_parsesMarkdownWrappedJson() {
        String markdownResponse = """
                ```json
                [
                  {"rank": 1, "score": 90, "reasoning": "Best photo"},
                  {"rank": 2, "score": 65, "reasoning": "Decent photo"}
                ]
                ```
                Some extra commentary from Claude that should be ignored.""";

        when(claudeApiService.callClaudeApiWithVision(anyString(), anyString(), any()))
                .thenReturn(markdownResponse);

        PhotoRankResponse response = photoRankingService.rankPhotos(twoPhotos());

        assertThat(response.getRankedPhotos()).hasSize(2);
        assertThat(response.getRankedPhotos().get(0).getScore()).isEqualTo(90.0);
        assertThat(response.getRankedPhotos().get(0).getReasoning()).isEqualTo("Best photo");
        // Extra commentary should NOT appear in reasoning
        assertThat(response.getRankedPhotos().get(0).getReasoning())
                .doesNotContain("commentary");
    }

    @Test
    void rankPhotos_parsesCodeBlockWithoutLanguageTag() {
        String response = """
                ```
                [{"rank": 1, "score": 80, "reasoning": "Nice"}, {"rank": 2, "score": 60, "reasoning": "OK"}]
                ```""";

        when(claudeApiService.callClaudeApiWithVision(anyString(), anyString(), any()))
                .thenReturn(response);

        PhotoRankResponse result = photoRankingService.rankPhotos(twoPhotos());

        assertThat(result.getRankedPhotos()).hasSize(2);
        assertThat(result.getRankedPhotos().get(0).getReasoning()).isEqualTo("Nice");
    }

    // ──────────── sorting ────────────

    @Test
    void rankPhotos_sortsResultsByRankAscending() {
        String json = """
                [
                  {"rank": 2, "score": 70, "reasoning": "Second"},
                  {"rank": 1, "score": 90, "reasoning": "First"}
                ]""";
        when(claudeApiService.callClaudeApiWithVision(anyString(), anyString(), any()))
                .thenReturn(json);

        PhotoRankResponse response = photoRankingService.rankPhotos(twoPhotos());

        assertThat(response.getRankedPhotos().get(0).getRank()).isEqualTo(1);
        assertThat(response.getRankedPhotos().get(1).getRank()).isEqualTo(2);
    }

    // ──────────── helpers ────────────

    private MockMultipartFile jpeg(String name) {
        return new MockMultipartFile("photos", name, "image/jpeg", new byte[100]);
    }

    private MultipartFile[] twoPhotos() {
        return new MultipartFile[]{jpeg("photo1.jpg"), jpeg("photo2.jpg")};
    }
}
