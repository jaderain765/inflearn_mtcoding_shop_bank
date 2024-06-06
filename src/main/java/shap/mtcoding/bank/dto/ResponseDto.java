package shap.mtcoding.bank.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 공통 응답 객체
 *
 * @param <T>
 */
@RequiredArgsConstructor
@Getter
public class ResponseDto<T> {

    private final Integer code;
    private final String msg;
    private final T data;

}
