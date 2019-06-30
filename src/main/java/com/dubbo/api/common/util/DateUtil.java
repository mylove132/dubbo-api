package com.dubbo.api.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String dateFormate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-mm hh:mm:ss");
        return simpleDateFormat.format(date);
    }
}
