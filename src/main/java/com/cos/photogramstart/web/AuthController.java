package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor // final 필드를 DI할 때 사용
@Controller // 1. IoC에 등록 , 2. 파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    // 1.
//    @Autowired
//    private AuthService authService;

    // 2.
//    private AuthService authService;
//
//    public AuthController(AuthService authService){
//        this.authService=authService;
//    }

    // 3. @RequiredArgsConstructor -> 필요
    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    // 회원가입버튼 -> /auth/signup -> /auth/signin
    // 회원가입버튼 X
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){ // key = value (x-www-form-urlencoded)
        log.info(signupDto.toString());

        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();

            for(FieldError error: bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(),error.getDefaultMessage());
                System.out.println("=======================");
                System.out.println(error.getDefaultMessage());
                System.out.println("=======================");
            }
            //throw new RuntimeException("유효성검사 실패함");
            throw new CustomValidationException("유효성검사 실패함",errorMap);
        }else{
            // User <- SignupDto
            User user = signupDto.toEntity();
            log.info(user.toString());
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);
            return "auth/signin";
        }
    }
    // @ResponseBody가 리턴타입 앞에 있으면 데이터를 응답
//    public @ResponseBody String signup(@Valid SignupDto signupDto, BindingResult bindingResult){ // key = value (x-www-form-urlencoded)
//        log.info(signupDto.toString());
//
//        if(bindingResult.hasErrors()){
//            Map<String,String> errorMap = new HashMap<>();
//
//            for(FieldError error: bindingResult.getFieldErrors()) {
//                errorMap.put(error.getField(),error.getDefaultMessage());
//                System.out.println("=======================");
//                System.out.println(error.getDefaultMessage());
//                System.out.println("=======================");
//            }
//            return "오류남";
//        }else{
//            // User <- SignupDto
//            User user = signupDto.toEntity();
//            log.info(user.toString());
//            User userEntity = authService.회원가입(user);
//            System.out.println(userEntity);
//            return "auth/signin";
//        }
//    }
}
