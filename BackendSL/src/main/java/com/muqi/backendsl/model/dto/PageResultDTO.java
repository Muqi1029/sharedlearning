package com.muqi.backendsl.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果类
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResultDTO<T> {


    private List<T> records;

    private Integer count;

}
