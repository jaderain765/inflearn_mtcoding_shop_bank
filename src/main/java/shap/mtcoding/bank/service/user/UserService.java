package shap.mtcoding.bank.service.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shap.mtcoding.bank.domain.user.User;
import shap.mtcoding.bank.domain.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    @Transactional
    public void 회원가입(JoinReqDto joinReqDto){
        // 1. 동일 유저네임 존재 검사
        Optional<User> userOP = userRepository.findByUsername(joinReqDto.getUsername());
//        if(userOP.isPresent()) throw new Exception("");

        // 2. 패스워드 인코딩

        // 3. dto 응답

    }

    @Getter
    @Setter
    public static class JoinReqDto {
        // 유효성 검사
        private String username;
        private String password;
        private String email;
        private String fullname;
    }
}

