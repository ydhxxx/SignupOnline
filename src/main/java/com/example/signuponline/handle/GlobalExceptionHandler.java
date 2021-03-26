package com.example.signuponline.handle;



import com.example.signuponline.common.LogResult;
import com.example.signuponline.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


/**
 * 异常处理拦截器
 * @author yudh
 * @since 2020-11-25
 */
@CrossOrigin
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    private static final String Log_Exception_Format = "Capture Exception By GlobalExceptionHandler: Code: %s ,Detail: %s";


    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public String runtimeExceptionHandler(RuntimeException ex) {
        return exceptionFormat(1, ex);
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException ex) {
        return exceptionFormat(2, ex);
    }

    /**
     * 类型转换异常
     */
    @ExceptionHandler(ClassCastException.class)
    public String classCastExceptionHandler(ClassCastException ex) {
        return exceptionFormat(3, ex);
    }

    /**
     * IO异常
     */
    @ExceptionHandler(IOException.class)
    public String ioExceptionHandler(IOException ex) {
        return exceptionFormat(4, ex);
    }

    /**
     * 未知方法异常
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public String noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return exceptionFormat(5, ex);
    }

    /**
     * 数组越界异常
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return exceptionFormat(6, ex);
    }

    /**
     * 400错误
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public String requestNotReadable(HttpMessageNotReadableException ex) {
        System.out.println("400..requestNotReadable");
        return exceptionFormat(7, ex);
    }

    /**
     * 400错误
     */
    @ExceptionHandler({TypeMismatchException.class})
    public String requestTypeMismatch(TypeMismatchException ex) {
        System.out.println("400..TypeMismatchException");
        return exceptionFormat(8, ex);
    }

    /**
     * 400错误
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String requestMissingServletRequest(MissingServletRequestParameterException ex) {
        System.out.println("400..MissingServletRequest");
        return exceptionFormat(9, ex);
    }

    /**
     * 405错误
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String request405(HttpRequestMethodNotSupportedException ex) {
        return exceptionFormat(10, ex);
    }

    /**
     * 406错误
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public String request406(HttpMediaTypeNotAcceptableException ex) {
        System.out.println("406...");
        return exceptionFormat(11, ex);
    }

    /**
     * 500错误
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public String server500(RuntimeException ex) {
        System.out.println("500...");
        return exceptionFormat(12, ex);
    }

    /**
     * 栈溢出
     */
    @ExceptionHandler({StackOverflowError.class})
    public String requestStackOverflow(StackOverflowError ex) {
        return exceptionFormat(13, ex);
    }

    /**
     * 其他错误
     */
    @ExceptionHandler({Exception.class})
    public String exception(Exception ex) {
        return exceptionFormat(14, ex);
    }

    /**
     * 自定义异常捕获
     */
    @ExceptionHandler({MyException.class})
    public String myException(MyException ex) {
        return exceptionFormat(ex.getCode(), ex);
    }

    private <T extends Throwable> String exceptionFormat(Integer code, T ex) {
        return LogResult.failed(code, ex.getMessage());
    }
}
