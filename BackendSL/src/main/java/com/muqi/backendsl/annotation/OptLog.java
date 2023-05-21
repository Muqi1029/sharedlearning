package com.muqi.backendsl.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    String optType() default "";
}
/*
注释定义@OptLog注记用于标记类中的方法。批注在运行时应用并记录方法。“optType（）” 方法返回一个字符串，默认值为空字符串。用于将元数据信息应用于可在运行时检索的方法。
 */