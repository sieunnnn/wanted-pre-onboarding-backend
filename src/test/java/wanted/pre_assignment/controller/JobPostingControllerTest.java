package wanted.pre_assignment.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import wanted.pre_assignment.dto.response.JobPostingDetailResponse;
import wanted.pre_assignment.dto.response.JobPostingResponse;
import wanted.pre_assignment.service.JobPostingService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class JobPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JobPostingService jobPostingService;

    @DisplayName(value = "채용 공고 리스트 테스트")
    @Test
    void getJobPostingList() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/job-posting")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        List<JobPostingResponse> returnedJobPostings = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertEquals(jobPostingService.getJobPostings(), returnedJobPostings);
    }

    @DisplayName(value = "채용 공고 수정 테스트")
    @Test
    void updateJobPosting() throws Exception {
        String requestPayload = "{" +
                "\"recruitmentPosition\": \"프론트엔드 주니어 개발자\"" + ","  +
                "\"recruitmentDetails\": \"프론트엔드 개발자 모집합니다.\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/job-posting/{companyId}/{jobPostingId}", 1, 5)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @DisplayName(value = "특정 채용 공고 상세 보기 테스트")
    @Test
    void getJobPostingDetail() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/job-posting/detail/{jobPostingId}", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        JobPostingDetailResponse jobPostingDetailResponse = objectMapper.readValue(responseBody, new TypeReference<>() {});
        assertEquals(jobPostingService.getJobPostingsDetail(2L), jobPostingDetailResponse);
    }
}