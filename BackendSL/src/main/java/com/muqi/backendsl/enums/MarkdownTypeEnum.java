package com.muqi.backendsl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 处理文件上传类型的枚举类
 */

@Getter
@AllArgsConstructor
public enum MarkdownTypeEnum {

    NORMAL("", "normalArticleImportStrategyImpl");

    private final String type;

    private final String strategy;

    public static String getMarkdownType(String name) {
        if (name == null) {
            return NORMAL.getStrategy();
        }
        for (MarkdownTypeEnum value : MarkdownTypeEnum.values()) {
            if (value.getType().equalsIgnoreCase(name)) {
                return value.getStrategy();
            }
        }
        return null;
    }
}