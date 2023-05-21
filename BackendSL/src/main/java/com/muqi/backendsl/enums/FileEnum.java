package com.muqi.backendsl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileEnum {

    JPG(".jpg", "jpg文件"),

    PNG(".png", "png文件"),

    JPEG(".jpeg", "jpeg文件"),

    WAV(".wav", "wav文件"),

    MD(".md", "markdown文件"),

    TXT(".txt", "txt文件");

    /**
     * 获取文件格式
     * @param extName 扩展名
     * @return {@link FileEnum} 文件格式
     */
    public static FileEnum getFileExt(String extName) {
        for (FileEnum value : FileEnum.values()) {
            if (value.getExtName().equalsIgnoreCase(extName)) {
                return value;
            }
        }
        return null;
    }

    private final String extName;

    private final String desc;

}
