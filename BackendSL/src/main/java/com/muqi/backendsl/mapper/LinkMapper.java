package com.muqi.backendsl.mapper;

import com.muqi.backendsl.entity.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
* @author mq
* @description 针对表【t_link】的数据库操作Mapper
* @createDate 2023-03-02 18:08:04
* @Entity com.muqi.backendsl.entity.Link
*/

@Repository
public interface LinkMapper extends BaseMapper<Link> {


}




