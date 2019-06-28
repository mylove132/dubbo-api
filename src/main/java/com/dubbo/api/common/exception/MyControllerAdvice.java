package com.dubbo.api.common.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice  //不指定包默认加了@Controller和@RestController都能控制
public class MyControllerAdvice {

    @ResponseBody
    @ExceptionHandler(value = UnknowException.class)
    public Map<String,Object> myExceptionHandler(UnknowException myex){
        Map<String,Object> map  = new HashMap<String,Object>();
        map.put("code",myex.getCode());
        map.put("message",myex.getMessage());
        map.put("method",myex.getMethod());
        map.put("info",myex.getDescinfo());
        return map;
    }
}
