package shap.mtcoding.bank.service.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shap.mtcoding.bank.config.dummy.DummyObject;
import shap.mtcoding.bank.domain.user.UserRepository;
import shap.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;
import shap.mtcoding.bank.dto.user.UserRespDto.JoinRespDto;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Spring 관련 Bean들이 하나도 없는 환경!!
@ExtendWith(MockitoExtension.class)
class UserServiceTest extends DummyObject {

    @InjectMocks
    private UserService userService;

    // 가짜로 만든 Repository 객체 (Spring ioc에 있는 진짜 Bean을 찾아서 가져온다.)
    @Mock
    private UserRepository userRepository;

    // 진짜가 사용하는 의존성을 가져온다.
    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void 회원가입_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("ssar");
        joinReqDto.setPassword("1234");
        joinReqDto.setEmail("ssar@nate.com");
        joinReqDto.setFullname("쌀");

        // stub
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
//        when(userRepository.findByUsername(any())).thenReturn(Optional.of(new User())); // 동일한 유저네임이 있다. 고 출력된다.

        // stub2
        when(userRepository.save(any())).thenReturn(newMockUser(1L, "ssar", "쌀"));

        // when
        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);
        System.out.println("테스트 : " + joinRespDto);

        // then
        Assertions.assertThat(joinRespDto.getId()).isEqualTo(1L);
        Assertions.assertThat(joinRespDto.getUsername()).isEqualTo("ssar");
    }

}