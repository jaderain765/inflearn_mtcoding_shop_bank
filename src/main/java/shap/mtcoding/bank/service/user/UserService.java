package shap.mtcoding.bank.service.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shap.mtcoding.bank.domain.user.User;
import shap.mtcoding.bank.domain.user.UserRepository;
import shap.mtcoding.bank.dto.user.UserReqDto;
import shap.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;
import shap.mtcoding.bank.dto.user.UserRespDto;
import shap.mtcoding.bank.dto.user.UserRespDto.JoinRespDto;
import shap.mtcoding.bank.ex.CustomApiException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 서비스는 DTO를 요청받고, DTO로 응답한다.
    @Transactional
    public JoinRespDto 회원가입(JoinReqDto joinReqDto){
        // 1. 동일 유저네임 존재 검사
        Optional<User> userOP = userRepository.findByUsername(joinReqDto.getUsername());
        if(userOP.isPresent())
            throw new CustomApiException("동일한 username이 존재합니다.");

        // 2. 패스워드 인코딩 + 회원가입
        /**
         * PS는 Persistence Context의 줄임말로 영속성 컨택스트를 뜻한다.
         */
        User userPS = userRepository.save(joinReqDto.toEntity(bCryptPasswordEncoder));

        // 3. dto 응답
        return new JoinRespDto(userPS);
    }




}

