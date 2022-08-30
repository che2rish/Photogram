package com.cos.photogramstart.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {

    private int code; // 1(성공), -1(실패)
    private String message;
    private T data; // errorMap뿐만 아니라 다른 것도 리턴 가능하게 하려고

}
