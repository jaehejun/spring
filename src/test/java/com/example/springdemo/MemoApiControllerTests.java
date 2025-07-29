package com.example.springdemo;

import com.example.springdemo.entity.Memo;
import com.example.springdemo.repository.MemoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //전체 애플리케이션 컨텍스트 로딩
@AutoConfigureMockMvc   //MockMvc 자동구성
public class MemoApiControllerTests {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    MemoRepository memoRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        memoRepository.deleteAll(); //DB 초기화
    }

    @DisplayName("addMemo: 새 메모 저장 성공")
    @Test
    public void addMemo() throws Exception {
        //given, 테스트 준비
        final String url = "/api/memo";
        final String memoText = "Test Memo Content";
        final Memo memo = Memo.builder().memoText(memoText).build();
        final String requestBody = objectMapper.writeValueAsString(memo); // JSON으로

        //when, 실행
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));

        //then, 검증
        result.andExpect(status().isCreated()); //HTTP상태코드가 201Created인지 검증

        List<Memo> list = memoRepository.findAll();
        assertThat(list.size()).isEqualTo(1);
        assertThat(list.get(0).getMemoText()).isEqualTo(memoText);
    }

    @DisplayName("findMemo: 메모 조회")
    @Test
    public void findMemo() throws Exception {
        //given
        final String url = "/api/memo/{id}";
        final String memoText = "테스트 메모";

        Memo memo = memoRepository.save(
                Memo.builder().memoText(memoText).build()
        );

        //when
        final ResultActions resultActions = mockMvc.perform(get(url,memo.getMno()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memoText").value(memoText));
    }
}
