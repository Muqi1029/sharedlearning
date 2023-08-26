package com.muqi.backendsl.model.vo;
/**
 * View Object
 */


import com.muqi.backendsl.enums.StatusCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.muqi.backendsl.enums.StatusCodeEnum.FAIL;
import static com.muqi.backendsl.enums.StatusCodeEnum.SUCCESS;


/**
 * ResultVO: 封装向前端返回的对象，使其包含返回状态、返回码等信息
 * 其中data field是返回的包含实际信息的对象
 *
 * @param <T>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("all")
public class ResultVO<T> {

    /**
     * the return state
     */
    private Boolean flag;

    /**
     * the return code
     */
    private Integer code;

    /**
     * return message
     */
    private String message;

    /**
     * return data
     */
    private T data;

    /**
     * static method
     *
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> ok() {
        return resultVO(true, SUCCESS.getCode(), SUCCESS.getDesc(), null);
    }

    public static <T> ResultVO<T> ok(T data) {
        return resultVO(true, SUCCESS.getCode(), SUCCESS.getDesc(), data);
    }

    public static <T> ResultVO<T> ok(T data, String message) {
        return resultVO(true, SUCCESS.getCode(), message, data);
    }

    public static <T> ResultVO<T> fail() {
        return resultVO(false, FAIL.getCode(), FAIL.getDesc(), null);
    }

    public static <T> ResultVO<T> fail(StatusCodeEnum statusCodeEnum) {
        return resultVO(false, statusCodeEnum.getCode(), statusCodeEnum.getDesc(), null);
    }

    public static <T> ResultVO<T> fail(String message) {
        return resultVO(false, message);
    }

    public static <T> ResultVO<T> fail(T data) {
        return resultVO(false, FAIL.getCode(), FAIL.getDesc(), data);
    }

    public static <T> ResultVO<T> fail(T data, String message) {
        return resultVO(false, FAIL.getCode(), message, data);
    }

    public static <T> ResultVO<T> fail(Integer code, String message) {
        return resultVO(false, code, message, null);
    }

    private static <T> ResultVO<T> resultVO(Boolean flag, String message) {
        return ResultVO.<T>builder()
                .flag(flag)
                .code(flag ? SUCCESS.getCode() : FAIL.getCode())
                .message(message).build();
    }

    /**
     * constructer method: initialize the object
     */
    private static <T> ResultVO<T> resultVO(Boolean flag, Integer code, String message, T data) {
        return ResultVO.<T>builder()
                .flag(flag)
                .code(code)
                .message(message)
                .data(data).build();
    }

}
