package com.ws.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ws.pojo.Result;
import com.ws.pojo.ResultCode;
 
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
 
    /**
     * 系统异常处理，比如：404,500
     * @param req
     * @param resp
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("", e);
        Result r = new Result();
        r.setMessage(e.getMessage());
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            r.failure(ResultCode.SYSTEM_404_ERROR, null);
        } else {
            r.failure(ResultCode.SYSTEM_500_ERROR, null);
        }
        return r;
    }
}


