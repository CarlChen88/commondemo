package com.cx.util;

import com.cx.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String,Object> dealException(Exception exception){
       /* HttpServletResponse httpServletResponse = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getResponse();
        try {
            PrintWriter pw = new PrintWriter(httpServletResponse.getOutputStream());
            pw.print(exception.getMessage());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
       Map<String, Object> result = new HashMap<>();
       result.put("result",exception.getCause().getMessage());
       return result;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Map<String,Object> dealBussinessException(Exception exception){
       /* HttpServletResponse httpServletResponse = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getResponse();
        try {
            PrintWriter pw = new PrintWriter(httpServletResponse.getOutputStream());
            pw.print(exception.getMessage());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Map<String,Object> result = new HashMap<>();
        result.put("result",exception.getMessage());
        return result;
    }
}
