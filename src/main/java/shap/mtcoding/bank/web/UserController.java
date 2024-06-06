package shap.mtcoding.bank.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shap.mtcoding.bank.dto.ResponseDto;
import shap.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;
import shap.mtcoding.bank.dto.user.UserRespDto;
import shap.mtcoding.bank.dto.user.UserRespDto.JoinRespDto;
import shap.mtcoding.bank.service.user.UserService;

/**
 * 사용자 컨트롤러
 *
 * "/api/s"가 아닌 이유는 회원가입을 하는데 있어서 인증이 필요하지 않기 때문.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 스프링에서는 기본적으로 데이터를 x-www-form-urlencoded 타입으로 가져오기 때문에
     * @RequestBody를 통해 받아야한다. (JSON타입으로 받을 수 있다.)
     *
     * @param joinReqDto
     */
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinReqDto joinReqDto){
        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입에 성공했습니다.", joinRespDto), HttpStatus.CREATED);
    }

}
