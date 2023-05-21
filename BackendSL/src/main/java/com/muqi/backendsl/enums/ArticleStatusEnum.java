package com.muqi.backendsl.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArticleStatusEnum {

    PUBLIC(0, "公开"),

    SECRET(1, "密码"),

    DRAFT(2, "草稿");

    private final Integer status;

    private final String desc;

}
