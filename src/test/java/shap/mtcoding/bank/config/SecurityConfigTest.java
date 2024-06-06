package shap.mtcoding.bank.config;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc // Mock(가짜) 환경에 MockMvc가 등록됨
class SecurityConfigTest {

    // 가짜 환경에 등록된 MockMvc를 DI함.
    @Autowired
    private MockMvc mvc;

    // 서버는 일관성 있게 에러가 리턴되어야 한다.
    // 내가 모르는 에러가 프론트한테 날라기지 않게, 내가 직접 다 제어하자.
    @Test
    @DisplayName("보안 테스트")
    public void authentication_test() throws Exception {
        // given


        // when
        ResultActions resultActions = mvc.perform(get("/api/s/hello"));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        String responseBody = response.getContentAsString();
        int httpStatusCode = response.getStatus();

        System.out.println("테스트 : " + responseBody);
        System.out.println("테스트 : " + httpStatusCode);

        //then
        assertThat(httpStatusCode).isEqualTo(401);

    }

    @Test
    @DisplayName("권한 테스트")
    public void authorization_test() throws Exception {
        // given


        // when
        ResultActions resultActions = mvc.perform(get("/api/admin/hello"));
        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        String responseBody = response.getContentAsString();
        int httpStatusCode = response.getStatus();

        System.out.println("테스트 : " + responseBody);
        System.out.println("테스트 : " + httpStatusCode);

        // 아직 로그인 하지 않았기 때문에 401이 맞다.

        //then
        assertThat(httpStatusCode).isEqualTo(401);

    }

}