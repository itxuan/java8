package com.xuan.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xuanzhongliang
 * @Date: 2018/8/3 09:39  八月
 * @Description:
 * @ModifyBy:
 */
public final class ThreadLocalDateFormat {

        private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd");
            }
        };
        public static Date format(String source) throws ParseException {

            Date format = df.get().parse(source);
            return format;
        }
}
