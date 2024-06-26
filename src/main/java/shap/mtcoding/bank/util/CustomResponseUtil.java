package shap.mtcoding.bank.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shap.mtcoding.bank.dto.ResponseDto;

public class CustomResponseUtil {

    private static final Logger log = LoggerFactory.getLogger(CustomResponseUtil.class);
    // 401 : 인증이 되지 않은 경우
    // 403 : 권한이 없는 경우
    public static void unAuthentication(HttpServletResponse response, String msg){
        try {
            ObjectMapper om = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(-1, msg, null);
            String responseBody = null;
            responseBody = om.writeValueAsString(responseDto);
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(401);
            response.getWriter().println(responseBody);
        }
        catch (Exception e) {
            log.error("서버 파싱 에러");
        }
    }
}
