package com.aether.sharecommon.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author 我走路带风
 * @since 2020/8/13 9:53
 */
@Slf4j
public class ExceptionUtil {

    /**
     * 打印异常信息
     */
    public static String getMessage(Exception e){
        String swStr = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            swStr = sw.toString();
        } catch (Exception exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return swStr;
    }

}
