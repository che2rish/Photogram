package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice // 모든 Exception을 다 낚아챔?
public class ControllerExceptionHandler {

    // RuntimeException이 발동하는 모든 Exception을 이 함수가 가로챔
//    @ExceptionHandler(RuntimeException.class)
//    public String validationException(RuntimeException e){
//        return e.getMessage(); // RuntimeExceptin이 String만 받기 때문에
//    }

//    @ExceptionHandler(CustomValidationException.class)
//    public Map<String, String> validationException(CustomValidationException e){
//        return e.getErrorMap();
//    }

    // CMRespDto, Script 비교
    // 1. 클라이언트에게 응답할 때는 Script가 좋음
    // 2. Ajax통신 - CMRespDto
    // 3. Android통신 - CMRespDto

//     @ExceptionHandler(CustomValidationException.class)
//    // 무엇을 리턴할지 모르겠으면 CMRespDto<?>로
//    public CMRespDto<Map<String,String>> validationException(CustomValidationException e){
//        return new CMRespDto(-1,e.getMessage(),e.getErrorMap());
//    }

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        return Script.back(e.getErrorMap().toString());
    }

}
