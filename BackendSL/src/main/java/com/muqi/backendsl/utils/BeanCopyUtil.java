package com.muqi.backendsl.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The BeanCopyUtil class offers a static method to obtain a copy of Object source.
 */
public class BeanCopyUtil {
    /**
    用 “newInstance（）” 方法创建目标类的新实例，然后用 Spring 框架的 “BeanUtils” 类中的 “copyProperties” 方法
     将属性从 “source” 对象复制到新实例。返回目标类的新实例，具有与“源”对象相同的属性。如果引发异常，则会捕获该异常并打印堆栈跟踪。
     **/

    public static <T> T copyObject(Object source, Class<T> target) {
        T dest = null;
        try {
            dest = target.getDeclaredConstructor().newInstance();
            if (null != source) {
                org.springframework.beans.BeanUtils.copyProperties(source, dest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }
}
